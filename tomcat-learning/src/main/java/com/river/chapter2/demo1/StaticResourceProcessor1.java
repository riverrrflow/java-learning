package com.river.chapter2.demo1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ${DESCRIPTION}
 *
 * @author riverrrflow
 * @since 2018-09-07-下午10:37
 */
public class StaticResourceProcessor1 {

    private static final Logger LOGGER = LoggerFactory.getLogger(StaticResourceProcessor1.class);

    public void process(Request1 request1, Response1 response1) {
        try {
            response1.sendStaticResource();
        } catch (Exception e) {
            LOGGER.error("static resource process error.", e);
        }
    }
}
