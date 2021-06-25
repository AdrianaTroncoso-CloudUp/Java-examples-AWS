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


    @Override
    public Response handleRequest(Request request, Context context) {
        String crearProductos ="";
        String nombre = request.getBody().getNombre();
        String descripcion = request.getBody().getDescripcion();
        String precio = request.getBody().getPrecio();
        String idCategoria = request.getBody().getIdCategoria();

        try {
            System.out.println("INICIANDO CONEXION BD ...");
            ConnectToRDS connectToRDS = new ConnectToRDS();

            HashMap<String, String> headers = request.getHeaders();
            context.getLogger().log(String.valueOf(headers));
            crearProductos = connectToRDS.createProducto(nombre, descripcion, precio, idCategoria);
            context.getLogger().log(crearProductos);
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

        return new Response( "Success, \"Nuevo Producto: Creado,\""
                +" \"Response: \""+crearProductos, 200);
    }

    private static java.sql.Date convert(java.util.Date fecha) {
        java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
        return fechaSQL;
    }
}
