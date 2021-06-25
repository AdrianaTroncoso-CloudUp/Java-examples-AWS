package smart;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.LambdaLogger;



import java.util.Map;

// Handler value: example.Handler
public class Tracking implements RequestHandler<Map<String,String>, String>{
    String gson = "Hola Mundo feliz";
    @Override
    public String handleRequest(Map<String,String> event, Context context)
    {
        String response = gson;
        return response;
    }
}