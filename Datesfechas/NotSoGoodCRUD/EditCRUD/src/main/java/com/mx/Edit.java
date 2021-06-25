package com.mx;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

public class Edit implements RequestHandler<Map<String, String>, String>{
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
    public String handleRequest(Map<String, String> event, Context context) {
        String persona_id= event.get("id");
        if ((persona_id.matches("")) == false) {
            String persona_nombre = ((event.get("nombre") == "") ? "Ejemplo" : event.get("nombre"));
            String persona_apaterno = ((event.get("apaterno") == "") ? "apaterno" : event.get("apaterno"));
            String persona_amaterno = ((event.get("amaterno") == "") ? "amaterno" : event.get("amaterno"));

            String persona_sexo;
            if ((event.get("sexo").matches("F")) || (event.get("sexo").matches("F")) == true) {
                persona_sexo = event.get("sexo");
            } else {
                persona_sexo = "F";
            }

            String persona_fecha_nacimiento = event.get("fecha_nacimiento");
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
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con = (Connection) DriverManager.getConnection(
                                "jdbc:mysql://database-1.cmhhj6fqpw4w.us-east-1.rds.amazonaws.com:3306/Gente?useSSL=false", "adminFeliz", "passwordFeliz");
                        context.getLogger().log("Test Started");

//                        String query = "insert into personas (persona_nombre, persona_apaterno, persona_amaterno, persona_fecha_nacimiento , persona_sexo) \n" +
//                                  "values(\"" + persona_nombre + "\", \"" + persona_apaterno + "\", \"" + persona_amaterno + "\",\"" + sqlDateCumple + "\", \"" + persona_sexo + "\");";
                        String query = "update personas set persona_nombre = \""+persona_nombre+"\", persona_apaterno= \""+persona_apaterno+"\" , persona_amaterno = \""+persona_amaterno+"\", persona_fecha_nacimiento = \""+cumple+"\", persona_sexo = \""+persona_sexo+"\" where persona_id = \""+persona_id+"\";";
                        //update personas set persona_nombre = "Tecla", persona_apaterno= "Piano" , persona_amaterno = "Musica", persona_fecha_nacimiento = "2000-09-30", persona_sexo = "F" where persona_id = "6";
                        Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                        statement.executeUpdate(query);
                        context.getLogger().log("query: " + query);
                        String output =
                            "\" Usuario editado - ID\": " + persona_id +"\""
                            + " , \" Nombre \" :\" " + persona_nombre + "\""
                            + " , \" Apellido Paterno\" :\" " + persona_apaterno + "\""
                            + " , \" Apellido Materno\" :\" " + persona_amaterno + "\""
                            + " , \" Fecha de Nacimiento\" :\" " + cumple + "\""
                            + " , \" Sexo:\" :\" " + persona_sexo + "\"";
                        output = "{" + output + "}";
                        resultado = output;
                        context.getLogger().log(output);

                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                        resultado = resultado + " " + e;
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                        resultado = resultado + " " + throwables;
                    }
                    return resultado;
                }
            } catch (ParseException e) {
                e.printStackTrace();
                resultado = resultado + " " + e;
            }
        }
    return resultado;
    }

    private static java.sql.Date convert(java.util.Date fecha){
        java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
        return fechaSQL;
    }
}
