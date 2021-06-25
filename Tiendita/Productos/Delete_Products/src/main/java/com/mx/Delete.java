package com.mx;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import conection.ConnectToRDS;
import jdk.nashorn.internal.ir.RuntimeNode;

import java.sql.*;
import java.util.HashMap;

public class Delete implements RequestHandler<Request, Response> {
    Statement statement;
    ResultSet resultSet;
    String output;
    private ConnectToRDS connectToRDS = null;
    String leerProducto ="";
    String borrarProducto ="";

    @Override
    public Response handleRequest(Request request, Context context) {
        int id = 0;
        try {
            context.getLogger().log("INICIANDO CONEXION BD ...");
            connectToRDS = new ConnectToRDS();

            HashMap<String, String> headers = request.getHeaders();
            id = request.getBody().getIdProducto();
            leerProducto = connectToRDS.readProducto(Integer.toString(id));
            context.getLogger().log(leerProducto);
            borrarProducto = connectToRDS.deleteUser(Integer.toString(id));
            context.getLogger().log(borrarProducto);
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

        return new Response( "Success, \"ID de Producto :\""+id
                +" \"Response: \""+leerProducto+" \"Operación: \""+borrarProducto, 200);
    }

}
