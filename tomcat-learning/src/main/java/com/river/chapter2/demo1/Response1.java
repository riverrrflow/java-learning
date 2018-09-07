package com.river.chapter2.demo1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import java.io.*;
import java.util.Locale;

/**
 * ${DESCRIPTION}
 *
 * @author riverrrflow
 * @since 2018-09-07-下午10:07
 */
public class Response1 implements ServletResponse {

    private static final Logger LOGGER = LoggerFactory.getLogger(Response1.class);

    private static final int BUFFER_SIZE = 1024;

    private OutputStream output;
    private Request1 request1;
    PrintWriter writer;

    public Response1(OutputStream outputStream, Request1 request1) {
        this.output = outputStream;
        this.request1 = request1;
    }

    public void sendStaticResource() throws IOException {
        byte[] bytes = new byte[BUFFER_SIZE];
        FileInputStream fis = null;
        try {
            File file = new File(HttpServer1.WEB_PROJECT_ROOT, request1.getUri());
            fis = new FileInputStream(file);

            String responseHeader = "HTTP/1.1 200 OK\r\n" +
                    "Content-Type: text/html\r\n" +
                    "\r\n";

            output.write(responseHeader.getBytes());

            int ch = fis.read(bytes, 0, BUFFER_SIZE);
            while (ch!=-1) {
                output.write(bytes, 0, ch);
                ch = fis.read(bytes, 0, BUFFER_SIZE);
            }
        }
        catch (FileNotFoundException e) {
            String errorMessage = "HTTP/1.1 404 File Not Found\r\n" +
                    "Content-Type: text/html\r\n" +
                    "Content-Length: 23\r\n" +
                    "\r\n" +
                    "<h1>File Not Found</h1>";
            output.write(errorMessage.getBytes());
        }
        finally {
            if (fis!=null) {
                fis.close();
            }
        }
    }

    public PrintWriter getWriter() throws IOException {
        // autoflush is true, println() will flush,
        // but print() will not.
        writer = new PrintWriter(output, true);
        return writer;
    }

    public String getCharacterEncoding() {
        return null;
    }

    public String getContentType() {
        return null;
    }

    public ServletOutputStream getOutputStream() throws IOException {
        return null;
    }

    public void setCharacterEncoding(String charset) {

    }

    public void setContentLength(int len) {

    }

    public void setContentType(String type) {

    }

    public void setBufferSize(int size) {

    }

    public int getBufferSize() {
        return 0;
    }

    public void flushBuffer() throws IOException {

    }

    public void resetBuffer() {

    }

    public boolean isCommitted() {
        return false;
    }

    public void reset() {

    }

    public void setLocale(Locale loc) {

    }

    public Locale getLocale() {
        return null;
    }
}
