package com.mx;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import conection.ConnectToRDS;
import com.google.gson.Gson;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class Read implements RequestHandler<Request, Response> {
    Statement statement;
    ResultSet resultSet;
    String output;
    private ConnectToRDS connectToRDS = null;
//    String leerProducto ="";
//    HashMap<String,HashMap> leerProducto = new HashMap<String, HashMap>();
//    HashMap<String,HashMap> data = new HashMap<String, HashMap>();
    List<HashMap> data = new ArrayList<>();
    List<HashMap> leerProducto = new ArrayList<>();
//    String data= "";
    String message ="";
    int codigo = 0;
    String json ="";
    Gson gson = new Gson();


    @Override
    public Response handleRequest(Request request, Context context) {
        int idProductosPerCategoria = 0;

        try {
            System.out.println("INICIANDO CONEXION BD ...");
            connectToRDS = new ConnectToRDS();

            HashMap<String, String> headers = request.getHeaders();
            idProductosPerCategoria = request.getBody().getIdProductosPerCategoria();
            leerProducto = connectToRDS.readProducto(Integer.toString(idProductosPerCategoria));
            if(leerProducto.contains("error")==false){
                message = "sucess";
                codigo=200;
                data.clear();
                data =leerProducto;
                json = gson.toJson(leerProducto );
            }else{
                message = "bad request";
                codigo=400;
                HashMap<String,String> data1 = new HashMap<String, String>();
                data1.put("Error", "La categoria de estos productos no esta activa.");
                HashMap<String,HashMap> data2 = new HashMap<String, HashMap>();
                data2.put("Error", data1);
                data.clear();
                data = new ArrayList<HashMap>(data2.values());
//                json = gson.toJson(data1);
            }

            context.getLogger().log("Resultado leerProducto: "+data);
            try{
                if(ConnectToRDS.conn!=null) {
                    ConnectToRDS.conn.close();
                }
            }catch(Exception ex){
                context.getLogger().log("Error en cerrar conexion BD: " +  ex.getMessage());
            }

            return new Response(new Date()+"",codigo,data ,message);
        } catch (Exception e) {
            e.printStackTrace();
            context.getLogger().log("error: "+e.getMessage());
            HashMap<String,String> data1 = new HashMap<String, String>();
            data1.put("Error", "Bad request.");
            HashMap<String,HashMap> data2 = new HashMap<String, HashMap>();
            data2.put("Error", data1);
            data.clear();
            data = new ArrayList<HashMap>(data2.values());
//            json = gson.toJson(data1);
            String response = new Response(new Date()+"",400,data,"error").toString();
            throw new RuntimeException(response);
        }
    }
}
