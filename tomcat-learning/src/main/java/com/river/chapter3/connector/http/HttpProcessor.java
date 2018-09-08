package com.river.chapter3.connector.http;

import java.net.Socket;

/**
 * ${DESCRIPTION}
 *
 * @author river
 * @create 2018-09-09 上午1:18
 */
public class HttpProcessor {

    private HttpConnector httpConnector;

    public HttpProcessor(HttpConnector httpConnector) {
        this.httpConnector = httpConnector;
    }

    public void process(Socket socket) {

    }
}
