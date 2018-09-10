package com.river.chapter3.connector.http;

/**
 * ${DESCRIPTION}
 *
 * @author riverrrflow
 * @since 2018-09-10-下午9:19
 */
public class HttpRequestLine {

    private String method;
    private String uri;
    private String protocol;

    public HttpRequestLine() {

    }

    public HttpRequestLine(String method, String uri, String protocol) {
        this.method = method;
        this.uri = uri;
        this.protocol = protocol;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
}
