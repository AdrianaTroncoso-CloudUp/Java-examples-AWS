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
    String leerCategorias ="";
    String borrarCategorias ="";

    @Override
    public Response handleRequest(Request request, Context context) {
        int idCategorias = 0;
        try {
            context.getLogger().log("INICIANDO CONEXION BD ...");
            connectToRDS = new ConnectToRDS();

            HashMap<String, String> headers = request.getHeaders();
            idCategorias = request.getBody().getIdCategorias();
            context.getLogger().log("idCategorias: "+idCategorias);
            leerCategorias = connectToRDS.readCategorias(Integer.toString(idCategorias));
            context.getLogger().log("Resultado: "+leerCategorias);
            borrarCategorias = connectToRDS.deleteCategorias(Integer.toString(idCategorias));
            context.getLogger().log("Borrador: "+borrarCategorias);
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

        return new Response( "Success, \"ID de Categoria :\""+idCategorias
                +" \"Response: \""+leerCategorias+" \"Operación: \""+borrarCategorias, 200);
    }

}
