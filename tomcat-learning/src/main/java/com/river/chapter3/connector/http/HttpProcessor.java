package com.river.chapter3.connector.http;

import com.river.chapter2.demo1.StaticResourceProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.OutputStream;
import java.net.Socket;

/**
 * ${DESCRIPTION}
 *
 * @author river
 * @create 2018-09-09 上午1:18
 */
public class HttpProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpProcessor.class);

    private HttpConnector httpConnector;

    public HttpProcessor(HttpConnector httpConnector) {
        this.httpConnector = httpConnector;
    }

    public void process(Socket socket) {

        SocketInputStream input = null;
        OutputStream output;

        try {
            input = new SocketInputStream(socket.getInputStream(), 2048);
            output = socket.getOutputStream();

            HttpRequest request = new HttpRequest(input);
            HttpResponse response = new HttpResponse(output);
            response.setRequest(request);

            response.setHeader("Server", "riverrrflow");
            parseRequest(input, output);
            parseHeaders(input);

            if (request.getRequestURI().startsWith("/servlet")) {
                ServletProcessor processor = new ServletProcessor();
                processor.process(request, response);
            } else {
                StaticResourceProcessor processor = new StaticResourceProcessor();
                processor.process(request, response);
            }

            socket.close();
        } catch (Exception e) {
            LOGGER.error("http processor handler error.", e);
        }

        LOGGER.debug("http processor handler over...");
    }

    private void parseRequest(SocketInputStream input, OutputStream output) {

    }
}
