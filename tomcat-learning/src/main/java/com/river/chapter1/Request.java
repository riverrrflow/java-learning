package com.river.chapter1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * ${DESCRIPTION}
 *
 * @author riverrrflow
 * @since 2018-09-06-下午7:30
 */
public class Request {

    private static final Logger LOGGER = LoggerFactory.getLogger(Request.class);

    private InputStream inputStream;
    private String uri;

    public Request(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getUri() {
        return uri;
    }

    public void parse() {
        StringBuffer stringBuffer = new StringBuffer(2048);

        int i;

        byte[] buffer = new byte[2048];

        try {
            i = inputStream.read(buffer);
        } catch (IOException e) {
            LOGGER.error("read error.", e);
            i = -1;
        }

        for (int j = 0; j < i; j++) {
            stringBuffer.append((char) buffer[j]);
        }

        LOGGER.info(stringBuffer.toString());

        uri = parseUri(stringBuffer.toString());

        LOGGER.info(uri);
    }

    public String parseUri(String requestString) {

        int index1, index2;

        index1 = requestString.indexOf(' ');

        if (index1 != -1) {
            index2 = requestString.indexOf(' ', index1 + 1);

            if (index2 > index1) {
                return requestString.substring(index1 + 1, index2);
            }
        }

        return null;

    }
}
