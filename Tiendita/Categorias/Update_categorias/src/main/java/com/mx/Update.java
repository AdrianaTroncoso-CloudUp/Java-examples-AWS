package com.mx;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import conection.ConnectToRDS;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class Update implements RequestHandler<Request, Response> {
    Statement statement;
    ResultSet resultSet;
    ResultSet resultSet1;

    java.util.Date cumple;
    String cumple1;
    java.util.Date today;
    java.util.Date parsed;
    String resultado = "Hola";
    int cuentaPersona = 0;

    @Override
    public Response handleRequest(Request request, Context context) {
        String updateCategoria ="";
        String categoriaId = request.getBody().getCategoriaId();
        String categoriaNombre = request.getBody().getCategoriaNombre();
        String categoriaDescripcion = request.getBody().getCategoria_descripcion();
        String categoriaActivo = request.getBody().getCategoria_activo();

        try {
            System.out.println("INICIANDO CONEXION BD ...");
            ConnectToRDS connectToRDS = new ConnectToRDS();
            HashMap<String, String> headers = request.getHeaders();
            context.getLogger().log(String.valueOf(headers));
            updateCategoria = connectToRDS.updateCategoria(categoriaId, categoriaNombre, categoriaDescripcion, categoriaActivo);
            context.getLogger().log(updateCategoria);
            try{
                if(ConnectToRDS.conn!=null) {
                    ConnectToRDS.conn.close();
                }
            }catch(Exception ex){
                System.out.println("Error en cerrar conexion BD: " +  ex.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Response( "Success, \"Categoria Editada: \""+ categoriaId
                +" \"Response: \""+updateCategoria, 200);
    }

    private static java.sql.Date convert(java.util.Date fecha) {
        java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
        return fechaSQL;
    }
}
