package com.mx;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import conection.ConnectToRDS;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;


public class Read implements RequestHandler<Request, Response> {
    Statement statement;
    ResultSet resultSet;
    String output;
    private ConnectToRDS connectToRDS = null;
    String leerCategoria ="";


    @Override
    public Response handleRequest(Request request, Context context) {
        int idCategoria = 0;

        try {
            System.out.println("INICIANDO CONEXION BD ...");
            connectToRDS = new ConnectToRDS();

            HashMap<String, String> headers = request.getHeaders();
            idCategoria = request.getBody().getIdCategoria();
            leerCategoria = connectToRDS.readCategoria(Integer.toString(idCategoria));
            context.getLogger().log(leerCategoria);
            try{
                if(ConnectToRDS.conn!=null) {
                    ConnectToRDS.conn.close();
                }
            }catch(Exception ex){
                context.getLogger().log("Error en cerrar conexion BD: " +  ex.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Response( "Success, \"ID :\""+idCategoria
                +" \"Response: \""+leerCategoria, 200);

    }

}
