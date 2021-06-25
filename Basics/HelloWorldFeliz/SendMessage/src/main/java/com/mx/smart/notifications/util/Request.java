package com.mx.smart.notifications.util;

import java.util.HashMap;

public class Request {

    String token;
    String medium;
    HashMap<String, String> attr;
    int templateId;

    public Request(){ }

    public Request(String token, String medium, HashMap<String, String> attr, int templateId) {
        this.token = token;
        this.medium = medium;
        this.attr = attr;
        this.templateId = templateId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public HashMap<String, String> getAttr() {
        return attr;
    }

    public void setAttr(HashMap<String, String> attr) {
        this.attr = attr;
    }

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    @Override
    public String toString() {
        return "Request{" +
                "token='" + token + '\'' +
                ", medium='" + medium + '\'' +
                ", attr=" + attr +
                ", templateId=" + templateId +
                '}';
    }
}
