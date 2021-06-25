package com.mx;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import conection.ConnectToRDS;
import com.google.gson.Gson;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;


public class Read implements RequestHandler<Request, Response> {
    Statement statement;
    ResultSet resultSet;
    String output;
    private ConnectToRDS connectToRDS = null;
//    String leerProducto ="";
    HashMap<String,String> leerProducto = new HashMap<String, String>();
    HashMap<String,String> data = new HashMap<String, String>();
//    String data= "";
    String message ="";
    int codigo = 0;
    String json ="";
    Gson gson = new Gson();


    @Override
    public Response handleRequest(Request request, Context context) {
        int idCategoria = 0;

        try {
            System.out.println("INICIANDO CONEXION BD ...");
            connectToRDS = new ConnectToRDS();

            HashMap<String, String> headers = request.getHeaders();
            idCategoria = request.getBody().getIdProducto();
            leerProducto = connectToRDS.readProducto(Integer.toString(idCategoria));
            if(leerProducto.containsKey("producto_nombre")){
                message = "sucess";
                codigo=200;
                data = leerProducto;
                json = gson.toJson(leerProducto );
            }else{
                message = "bad request";
                codigo=400;
                HashMap<String,String> data1 = new HashMap<String, String>();
                data1.put("Error", "La categoria de estos productos no esta activa.");
                data = data1;
                json = gson.toJson(data1);
            }

            context.getLogger().log("Resultado leerProducto: "+leerProducto);
            try{
                if(ConnectToRDS.conn!=null) {
                    ConnectToRDS.conn.close();
                }
            }catch(Exception ex){
                context.getLogger().log("Error en cerrar conexion BD: " +  ex.getMessage());
            }
            return new Response(new Date()+"",codigo,data,message);

        } catch (Exception e) {
            e.printStackTrace();
            context.getLogger().log("error: "+e.getMessage());
            HashMap<String,String> data1 = new HashMap<String, String>();
            data1.put("Error", "Bad request.");
            json = gson.toJson(data1);
            String response = new Response(new Date()+"",400,data1,"error").toString();
            throw new RuntimeException(response);
        }
    }
}
