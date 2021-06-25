package com.mx;

import java.util.HashMap;

public class Request {

    String method;
    Body body;
    HashMap<String, String> headers;

    public Request(){
    }

    public Request(String method, Body body, HashMap<String, String> headers) {
        this.method = method;
        this.body = body;
        this.headers = headers;
    }



    public String getMethod() {

        return method;
    }

    public void setMethod(String method) {

        this.method = method;
    }

    public Body getBody() {

        return body;
    }

    public void setBody(Body body) {

        this.body = body;
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(HashMap<String, String> headers) {

        this.headers = headers;
    }

}
