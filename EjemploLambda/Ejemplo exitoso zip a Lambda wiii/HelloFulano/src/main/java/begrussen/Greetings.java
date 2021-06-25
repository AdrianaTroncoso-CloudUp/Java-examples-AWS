package begrussen;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

import java.util.Map;
import java.util.Arrays;

public class Greetings implements RequestHandler <Map<String,String>, String>{
    String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    String[] times = {"morning", "afternoon", "evening", "night", "day"};

    @Override
    public String handleRequest(Map<String,String> event, Context context)
    {
        String name = ((event.get("name") == "") ? "you" : event.get("name"));
        String city = ((event.get("city") == "") ? "the earth": event.get("city"));
        String time = ((Arrays.asList(times).contains(event.get("time")) == false) ? "day" : event.get("time"));
        String day = ((Arrays.asList(days).contains(event.get("day")) == false) ? "week": event.get("day"));


        String greeting = "Good " + time + ", " + name + " of "+ city + ".";
        greeting += " Happy " + day +"!";
        String message = "Mensaje de regreso.";
        int statusCode = 200;

//        return greeting;
        return "{"
                + "\n message : " + message
                + "\n, statusCode : "+statusCode
                + "\n, data : " + greeting + "\n"
                + "}";
    }
}