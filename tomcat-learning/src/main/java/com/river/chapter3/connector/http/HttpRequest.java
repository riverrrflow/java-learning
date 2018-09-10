package com.river.chapter3.connector.http;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

/**
 * ${DESCRIPTION}
 *
 * @author riverrrflow
 * @since 2018-09-10-下午1:28
 */
public class HttpRequest implements HttpServletRequest {
    private String contentType;
    private int contentLength;
    private InetAddress inetAddress;
    private InputStream input;
    private String method;
    private String protocol;
    private String queryString;
    private String requestURI;
    private String serverName;
    private int serverPort;
    private Socket socket;
    private boolean requestedSessionCookie;
    private String requestedSessionId;
    private boolean requestedSessionURL;

    protected HashMap attributes = new HashMap();

    protected String authorization = null;

    protected String contextPath = "";

    protected ArrayList cookies = new ArrayList();

    protected static ArrayList empty = new ArrayList();

    protected SimpleDateFormat formats[] = {
            new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US),
            new SimpleDateFormat("EEEEEE, dd-MMM-yy HH:mm:ss zzz", Locale.US),
            new SimpleDateFormat("EEE MMMM d HH:mm:ss yyyy", Locale.US)
    };

    protected HashMap headers = new HashMap();

//    protected ParameterMap parameters = null;
}
