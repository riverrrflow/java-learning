package com.river.chapter3.connector.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ${DESCRIPTION}
 *
 * @author river
 * @create 2018-09-09 上午1:08
 */
public class HttpConnector implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpConnector.class);

    private boolean stopped;
    private String schema = "http";

    public String getSchema() {
        return schema;
    }

    public void run() {
        ServerSocket serverSocket = null;
        int port = 2333;

        try {
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.01"));
        } catch (IOException e) {
            LOGGER.error("server socket init error.", e);
            throw new RuntimeException(e);
        }

        while (!stopped) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
            } catch (Exception e) {
                continue;
            }

            HttpProcessor processor = new HttpProcessor(this);
            processor.process(socket);
        }
    }

    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }
}
