
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

    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("servlet init.");
    }

    public ServletConfig getServletConfig() {
        return null;
    }

    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("servlet service.");
        PrintWriter writer = servletResponse.getWriter();
        writer.println("HTTP/1.1 200 OK\r\n" +
                "Content-Type: text/html\r\n" +
                "\r\n");
        writer.println("Hello. riverrrflow");
        writer.print("flow");
    }

    public String getServletInfo() {
        return null;
    }

    public void destroy() {
        System.out.println("servlet destory.");
    }
}