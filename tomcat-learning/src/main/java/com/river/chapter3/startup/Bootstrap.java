package com.river.chapter3.startup;

import com.river.chapter3.connector.http.HttpConnector;

/**
 * ${DESCRIPTION}
 *
 * @author river
 * @create 2018-09-09 上午1:06
 */
public class Bootstrap {

    public static void main(String[] args) {
        HttpConnector connector = new HttpConnector();
        connector.start();
    }
}
