package com.river.chapter1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * ${DESCRIPTION}
 *
 * @author riverrrflow
 * @since 2018-09-06-下午7:30
 */
public class Response {

    private static final Logger LOGGER = LoggerFactory.getLogger(Response.class);

    private OutputStream outputStream;
    private Request request;

    public Response(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void sendStaticResource() throws IOException {
        byte[] bytes = new byte[1024];

        FileInputStream fis = null;

        try {
            LOGGER.info("Web root = {}", HttpServer.WEB_PROJECT_ROOT);
            File file = new File(HttpServer.WEB_PROJECT_ROOT, request.getUri());

            LOGGER.info("file path = {}", file.getPath());

            if (file.exists()) {

                String responseHeader = "HTTP/1.1 200 OK\r\n" +
                        "Content-Type: text/html\r\n" +
                        "\r\n";

                outputStream.write(responseHeader.getBytes());

                fis = new FileInputStream(file);
                int ch = fis.read(bytes, 0, 1024);
                while (ch != -1) {
                    outputStream.write(bytes, 0, ch);
                    ch = fis.read(bytes, 0, 1024);
                }
            } else {
                String errMsg = "HTTP/1.1 404 File Not Found\r\n" +
                        "Content-Type: text/html\r\n" +
                        "Content-Length: 23\r\n" +
                        "\r\n" +
                        "<h1>File Not Found</h1>";
                outputStream.write(errMsg.getBytes());
            }
        } catch (Exception e) {
            LOGGER.error("get resource error.", e);
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
    }
}
