package com.river.chapter2.demo1;

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
 * ${DESCRIPTION}
 *
 * @author riverrflow
 * @since 2018-09-07-下午9:37
 */
public class HttpServer1 {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpServer1.class);

    private static final String SHUTDOWN_COMMAND = "/shutdown";

    private boolean shutdown = false;

    public static final String WEB_PROJECT_ROOT;

    static{
        URL webrootURL = HttpServer1.class.getClassLoader().getResource("webroot");
        WEB_PROJECT_ROOT = webrootURL.getFile();
    }

    public static void main(String[] args) {
        HttpServer1 httpServer1 = new HttpServer1();
        httpServer1.await();
    }

    public void await() {
        ServerSocket serverSocket;

        try {
            serverSocket = new ServerSocket(2333, 1, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            LOGGER.error("server socket init error.", e);
            throw new RuntimeException(e);
        }

        while(!shutdown) {
            Socket socket;
            InputStream inputStream;
            OutputStream outputStream;

            try {
                socket = serverSocket.accept();
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();

                Request1 request1 = new Request1(inputStream);
                request1.parse();

                Response1 response1 = new Response1(outputStream, request1);

                String uri = request1.getUri();

                if (uri.startsWith("/servlet/")) {
                    ServletProcessor1 processor = new ServletProcessor1();
                    processor.process(request1, response1);
                } else {
                    StaticResourceProcessor1 processor = new StaticResourceProcessor1();
                    processor.process(request1, response1);
                }

//                response1.sendStaticResource();

                socket.close();
                shutdown = request1.getUri().equals(SHUTDOWN_COMMAND);

            } catch (Exception e) {
                LOGGER.error("server handler request error.", e);
            }
        }
    }
}
