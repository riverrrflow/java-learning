package com.river.chapter2.demo1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.OutputStream;

/**
 * ${DESCRIPTION}
 *
 * @author riverrrflow
 * @since 2018-09-07-下午10:07
 */
public class Response1 {

    private static final Logger LOGGER = LoggerFactory.getLogger(Response1.class);

    private OutputStream outputStream;
    private Request1 request1;

    public Response1(OutputStream outputStream, Request1 request1) {
        this.outputStream = outputStream;
        this.request1 = request1;
    }


}
