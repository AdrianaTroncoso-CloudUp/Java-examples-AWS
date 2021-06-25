package com.mx;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import conection.ConnectToRDS;

import java.sql.*;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

public class Create implements RequestHandler<Request, Response> {
    Statement statement;
    ResultSet resultSet;
    ResultSet resultSet1;

    int cuentaPersona = 0;

    @Override
    public Response handleRequest(Request request, Context context) {
        String crearCategoria ="";
        //String categoriaId = request.getBody().getCategoriaId();
        String categoriaNombre = request.getBody().getCategoriaNombre();
        String categoria_descripcion = request.getBody().getCategoria_descripcion();
        String categoria_activo = request.getBody().getCategoria_activo();

        try {
            System.out.println("INICIANDO CONEXION BD ...");
            ConnectToRDS connectToRDS = new ConnectToRDS();

            HashMap<String, String> headers = request.getHeaders();
            context.getLogger().log(String.valueOf(headers));
            crearCategoria = connectToRDS.createCategory(categoriaNombre, categoria_descripcion, categoria_activo);
            context.getLogger().log(crearCategoria);
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

        return new Response( "Success, \"Nueva Categoria: Creada,\""
                +" \"Response: \""+crearCategoria, 200);
    }

    private static java.sql.Date convert(java.util.Date fecha) {
        java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
        return fechaSQL;
    }
}
