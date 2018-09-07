package com.river.chapter1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;

/**
 * http服务器
 *
 * @author riverrrflow
 * @since 2018-09-06-下午5:26
 */
public class HttpServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpServer.class);

    private boolean shutdown = false;

    private static final String SHUTDOWN_COMMAND = "/shutdown";

    public static final String WEB_PROJECT_ROOT;

    static{
        URL webrootURL = HttpServer.class.getClassLoader().getResource("webroot");
        WEB_PROJECT_ROOT = webrootURL.getFile();
    }


    public static void main(String[] args) {
        HttpServer httpServer = new HttpServer();
        httpServer.await();
    }

    public void await() {
        ServerSocket serverSocket;

        try {
            serverSocket = new ServerSocket(2333, 1, InetAddress.getByName("127.0.0.1"));
            LOGGER.debug("server");
        } catch (IOException e) {
            LOGGER.error("server shutdown!!!", e);
            throw new RuntimeException(e);
        }

        while (!shutdown) {
            Socket socket;
            InputStream inputStream;
            OutputStream outputStream;

            try {
                socket = serverSocket.accept();
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();

                Request request = new Request(inputStream);
                request.parse();

                Response response = new Response(outputStream);
                response.setRequest(request);
                response.sendStaticResource();

                socket.close();
                shutdown = request.getUri().equals(SHUTDOWN_COMMAND);

            } catch (Exception e) {
                LOGGER.error("handler request error.", e);
            }
        }

        LOGGER.info("server is shutdown!");
    }
}
