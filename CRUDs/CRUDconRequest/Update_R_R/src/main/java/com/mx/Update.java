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
        String updateUser ="";
        int persona_id= request.getBody().getIdUser();
        String persona_nombre = request.getBody().getNombre();
        String persona_apaterno = request.getBody().getApaterno();
        String persona_amaterno = request.getBody().getAmaterno();
        String persona_sexo=request.getBody().getSexo();

        String persona_fecha_nacimiento = request.getBody().getFecha_nacimiento();
        java.util.Date hoy = new java.util.Date();
        java.sql.Date sqlDate = convert(hoy);
        java.util.Date cumple = null;
        try {
            cumple = new SimpleDateFormat("yyyy-MM-dd").parse(persona_fecha_nacimiento);
            java.sql.Date sqlDateCumple = convert(cumple);
            context.getLogger().log("Original :" + persona_fecha_nacimiento + " java.sql.Date cumple es : " + sqlDateCumple);
            if (sqlDateCumple.compareTo(sqlDate) > 0) {
                System.out.println("La fecha de cumpleaños tiene un formato incorrecto.");
            } else {
                String fechasBien = "hoy:" + sqlDate + " ; fecha de nacimiento:" + sqlDateCumple + " .";
                context.getLogger().log(fechasBien);
                context.getLogger().log("id: " + persona_id + ",nombre: " + persona_nombre + ", apaterno:" + persona_apaterno + ", amaterno:" + persona_amaterno +
                        ", fecha_nacimiento:" + sqlDateCumple + ", sexo:" + persona_sexo + ".");
                try {
                    System.out.println("INICIANDO CONEXION BD ...");
                    ConnectToRDS connectToRDS = new ConnectToRDS();
                    HashMap<String, String> headers = request.getHeaders();
                    context.getLogger().log(String.valueOf(headers));
                    updateUser = connectToRDS.updateUser(persona_id, persona_nombre, persona_apaterno, persona_amaterno, persona_fecha_nacimiento, persona_sexo);
                    context.getLogger().log(updateUser);
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
                return new Response( "Success, \"Nuevo Usuario: Creado,\""
                        +" \"Response: \""+updateUser, 200);
            }
        } catch (ParseException e) {
            e.printStackTrace();
            resultado = resultado + " " + e;
        }
        return new Response( "Success, \"Usuario Editado: \""+ persona_id
                +" \"Response: \""+updateUser, 200);
    }

    private static java.sql.Date convert(java.util.Date fecha) {
        java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
        return fechaSQL;
    }
}
