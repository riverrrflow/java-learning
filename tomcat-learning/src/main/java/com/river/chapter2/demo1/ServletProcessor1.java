package com.river.chapter2.demo1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

/**
 * ${DESCRIPTION}
 *
 * @author riverrrflow
 * @since 2018-09-07-下午10:41
 */
public class ServletProcessor1 {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServletProcessor1.class);

    public void process(Request1 request1, Response1 response1) {
        String uri = request1.getUri();
        String servletName = uri.substring(uri.lastIndexOf("/") + 1);

        LOGGER.debug("ServletName = {}", servletName);

        URLClassLoader loader = null;

        try {

            URL[] urls = new URL[1];
            URLStreamHandler urlStreamHandler = null;

            File file = new File(HttpServer1.WEB_PROJECT_ROOT);

            String repository = (new URL("file", null,
                    file.getCanonicalPath() + File.separator)).toString();

            urls[0] = new URL(null, repository, urlStreamHandler);

            loader = new URLClassLoader(urls);
        } catch (Exception e) {
            LOGGER.error("servlet process error.", e);
        }

        Class<?> myClass = null;

        try {
            myClass = loader.loadClass(servletName);
        } catch (ClassNotFoundException e) {
            LOGGER.error("can't find class.", e);
        }

        Servlet servlet = null;

        try {
            servlet = (Servlet) myClass.newInstance();
            servlet.service(request1, (ServletResponse) response1);
        } catch (Exception e) {
            LOGGER.error("servlet service error.", e);
        }

        LOGGER.info("servlet process over.");
    }
}
