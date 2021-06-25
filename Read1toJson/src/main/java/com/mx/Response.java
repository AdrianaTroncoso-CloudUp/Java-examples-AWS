package com.mx;
import javax.xml.transform.Templates;
import java.util.HashMap;
import java.util.List;
import com.google.gson.Gson;

public class Response {
    String message;
    int status;
    HashMap<String,String> values = new HashMap<String, String>();
    String timestamp;

    public Response(){

    }

    public Response(String timestamp,int status, HashMap values,String message  ){
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

    public HashMap getValues() {

        return values;
    }

    public void setValues(HashMap values) {

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
