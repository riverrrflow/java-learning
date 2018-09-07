package com.river.chapter2.demo1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * ${DESCRIPTION}
 *
 * @author riverxu
 * @since 2018-09-07-下午11:01
 */
public class PrimitiveServlet implements Servlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrimitiveServlet.class);

    public void init(ServletConfig servletConfig) throws ServletException {
        LOGGER.info("servlet init.");
    }

    public ServletConfig getServletConfig() {
        return null;
    }

    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        LOGGER.info("servlet service.");
        PrintWriter writer = servletResponse.getWriter();
        writer.println("Hello. river");
        writer.print("flow");
    }

    public String getServletInfo() {
        return null;
    }

    public void destroy() {
        LOGGER.info("servlet destory.");
    }
}