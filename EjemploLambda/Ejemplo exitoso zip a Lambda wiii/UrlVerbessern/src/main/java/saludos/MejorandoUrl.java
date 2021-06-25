package saludos;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.HashMap;

public class MejorandoUrl implements RequestHandler <Request, Response>{

    @Override
    public Response handleRequest(Request request, Context context)
    {
        try{
            //Cabecera de autorización
            HashMap<String, String> headers = request.getHeaders();
            int longitud = request.getBody().getLength();
            int comienzo = request.getBody().getStart();

            return new Response( "Success, \"Length :\""+longitud
                    +" \"Start: \""+comienzo, 200);
        }
        catch (Exception e){
            String error = new Response("\"Error: \""+ e.getMessage(), 404).toString();
            throw new RuntimeException(error);
        }

    }
}