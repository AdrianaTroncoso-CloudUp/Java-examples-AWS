package com.mx;
import javax.xml.transform.Templates;
import java.util.HashMap;
import java.util.List;

public class Response {
    String message;
    int status;
    String values;
    String timestamp;

    public Response(){

    }

    public Response(String timestamp,int status, String values,String message  ){
        this.timestamp = timestamp;
        this.status = status;
        this.values = values;
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {

        return status;
    }

    public void setStatus(int status) {

        this.status = status;
    }

    public String getValues() {

        return values;
    }

    public void setValues(String values) {

        this.values = values;
    }


    public String getMessage(){

        return message;
    }

    public void setMessage(String message) {

        this.message = message;
    }

    @Override
    public String toString() {
//        return "{" +
//                "message='" + message + '\'' +
//                ", status=" + status +
//                ", data=" + data +
//                ", timestamp='" + timestamp + '\'' +
//                '}';
        return "{"
                + ", \"timestamp\":\"" + timestamp + "\""
                + ", \"status\":" + status
                + ", \"data\":\"" + values + "\""
                + "\"message\":\"" + message + "\""
                + "}";
    }
}
