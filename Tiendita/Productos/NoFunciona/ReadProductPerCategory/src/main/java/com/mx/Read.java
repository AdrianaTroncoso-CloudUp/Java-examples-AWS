package com.mx;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

import conection.ConnectToRDS;


import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;


public class Read implements RequestHandler<Request, Response> {
    Statement statement;
    ResultSet resultSet;
    String output;
    private ConnectToRDS connectToRDS = null;
    String leerProducto;
    private LambdaLogger lambdaLogger;

    @Override
    public Response handleRequest(Request request, Context context) {
        int id = 0;
        lambdaLogger = context.getLogger();
        lambdaLogger.log("Iniciando");
        try {
            System.out.println("INICIANDO CONEXION BD ...");
            connectToRDS = new ConnectToRDS();

            HashMap<String, String> headers = request.getHeaders();
            id = request.getBody().getIdCategoria();
            lambdaLogger.log("IdCategoria: "+id);
            Response response = connectToRDS.readProducto(Integer.toString(id));
            context.getLogger().log(String.valueOf(leerProducto));
            try{
                if(ConnectToRDS.conn!=null) {
                    ConnectToRDS.conn.close();
                }
            }catch(Exception ex){
                System.out.println("Error en cerrar conexion BD: " +  ex.getMessage());
            }
            return response;
        } catch (Exception e) {
            List<HashMap> values = new ArrayList<HashMap>();
            HashMap<String,String> data = new HashMap<>();
            e.printStackTrace();
            data.put("Error", e.getMessage());
            values = new ArrayList(data.values());
            String response = new Response("error",400,values,new Date()+"").toString();
            throw new RuntimeException(response);
        }

    }

}
