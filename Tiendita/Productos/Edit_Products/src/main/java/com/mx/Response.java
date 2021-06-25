package com.mx;
import javax.xml.transform.Templates;
import java.util.List;

public class Response {
    String message;
    int statusCode;
    List<Templates> data;

    public Response(){

    }
    public Response(String message, int statusCode){
        this.message = message;
        this.statusCode = statusCode;
    }

    public Response(String message, int statusCode, List<Templates> data){
        this.message = message;
        this.statusCode = statusCode;
        this.data = data;
    }

    public String getMessage(){

        return message;
    }

    public void setMessage(String message) {

        this.message = message;
    }

    public int getStatusCode() {

        return statusCode;
    }

    public void setStatusCode(int statusCode) {

        this.statusCode = statusCode;
    }

    public List<Templates> getData() {

        return data;
    }

    public void setData(List<Templates> data) {

        this.data = data;
    }

    public String toString(){
        return "{"
                + "\" message\": " + message
                + " , \" statusCode\" :\" " + statusCode + "\""
                + " , \" data\" :\" " + data
                + "}";
    }

}
