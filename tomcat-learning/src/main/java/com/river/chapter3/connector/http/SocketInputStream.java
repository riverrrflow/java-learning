package com.river.chapter3.connector.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

/**
 * ${DESCRIPTION}
 *
 * @author riverrrflow
 * @since 2018-09-10-下午9:27
 */
public class SocketInputStream {

    private static final Logger LOGGER = LoggerFactory.getLogger(SocketInputStream.class);

    private static final byte CR = '\r';
    private static final byte CF = '\n';

    private InputStream inputStream;
    private byte[] buf;
    private StringBuffer sb;

    public SocketInputStream(InputStream inputStream, int length) {
        this.inputStream = inputStream;
        this.buf = new byte[length];
        this.sb = new StringBuffer(length);
    }

    public void readRequestLine(HttpRequestLine requestLine) {

        int i = 0;

        try {
            i = inputStream.read(buf);
        } catch (Exception e) {
            LOGGER.error("read error.", e);
            i = -1;
        }

        for (int j = 0; j < i; i++) {
            sb.append((char)buf[j]);
        }

        parseUri(sb.toString(), requestLine);
    }

    private void parseUri(String requestStr, HttpRequestLine requestLine) {
        int lineIndex = requestStr.indexOf("\n");

        String lineStr = requestStr.substring(0, lineIndex);
        LOGGER.debug("lineStr = {}", lineStr);

        int index1 = lineStr.indexOf(" ");
        int index2 = lineStr.indexOf(" ", index1 + 1);

        requestLine.setMethod(lineStr.substring(0, index1));
        requestLine.setUri(lineStr.substring(index1 + 1, index2));
        requestLine.setProtocol(lineStr.substring(index2 + 1, lineIndex));

        LOGGER.debug("requestLine = {}", requestLine);
    }
}
