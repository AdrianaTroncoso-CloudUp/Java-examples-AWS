package com.mx;
import javax.xml.transform.Templates;
import java.util.HashMap;
import java.util.List;

public class Response {
    String message;
    int status;
    List<HashMap> values;
   String timestamp;

    public Response(){

    }

    public Response(String message, int status, List<HashMap> values, String timestamp){
        this.message = message;
        this.status = status;
        this.values = values;
        this.timestamp = timestamp;
    }


    public String getMessage(){

        return message;
    }

    public void setMessage(String message) {

        this.message = message;
    }

    public int getStatus() {

        return status;
    }

    public void setStatus(int status) {

        this.status = status;
    }

    public List<HashMap> getValues() {

        return values;
    }

    public void setValues(List values) {

        this.values = values;
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
