package com.mx;
import javax.xml.transform.Templates;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Response {
    String message;
    int status;
    List<HashMap> values = new ArrayList<>();
    String timestamp;

    public Response(){

    }

    public Response(String timestamp,int status, List values,String message  ){
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

    public List getValues() {

        return values;
    }

    public void setValues(List values) {

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
