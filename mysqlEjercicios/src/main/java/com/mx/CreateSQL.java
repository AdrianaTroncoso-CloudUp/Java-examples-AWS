package com.mx;

import java.sql.PreparedStatement;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.sql.*;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Map;
import java.util.Arrays;

public class CreateSQL {
    Statement statement;
    ResultSet resultSet;

    public static void main(String args[]) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                Connection con = (Connection) DriverManager.getConnection(
                        "jdbc:mysql://database-1.cmhhj6fqpw4w.us-east-1.rds.amazonaws.com:3306/Gente?useSSL=false", "adminFeliz", "passwordFeliz");
                System.out.println("Test Started");
                String cuenta = "select count(*) as cuenta from Gente.personas";
                Statement statement1 = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet resultSet1 = statement1.executeQuery(cuenta);
                int cuentaPersona = 0;
                if (resultSet1.next()) {
                    cuentaPersona = resultSet1.getInt("cuenta");
                    cuentaPersona = cuentaPersona + 1;
                }
                String cuentaString = Integer.toString(cuentaPersona);
                System.out.println("Número de personas: " + cuenta + " ID nuevo:" + cuentaPersona);

                String persona_nombre = "Floripondia";
                String persona_apaterno = "Fulanitos";
                String persona_amaterno = "Felices";
                String persona_sexo = "F";
                String persona_fecha_nacimiento = "1999-03-23";
                java.util.Date hoy = new java.util.Date();
                java.sql.Date sqlDate = convert(hoy);
                Date cumple = null;
                try {
                    cumple = new SimpleDateFormat("yyyy-MM-dd").parse(persona_fecha_nacimiento);
                    java.sql.Date sqlDateCumple = convert(cumple);
                    System.out.println("Original :" + persona_fecha_nacimiento + " java.sql.Date cumple es : " + sqlDateCumple);
                    String fechasBien = "hoy:" + hoy + " ; fecha de nacimiento:" + cumple + " .";
                    if (cumple.compareTo(hoy) > 0) {
                        System.out.println("La fecha de cumpleaños tiene un formato incorrecto.");
                    } else {
//                context.getLogger().log(

                        System.out.println(fechasBien);
                        System.out.println("nombre: " + persona_nombre + ", apaterno:" + persona_apaterno + ", amaterno:" + persona_amaterno +
                                ", fecha_nacimiento:" + sqlDateCumple + ", sexo:" + persona_sexo + ".");
                        String query = "insert into personas (persona_nombre, persona_apaterno, persona_amaterno, persona_fecha_nacimiento , persona_sexo) \n" +
                                "values(\""+persona_nombre+"\", \""+persona_apaterno+"\", \""+persona_amaterno+"\",\""+sqlDateCumple+"\", \""+persona_sexo+"\");";
//                        String query = "insert into personas (persona_nombre, persona_apaterno, persona_amaterno, persona_fecha_nacimiento , persona_sexo) \n" +
//                                "values(\"Fulanita\", \"Rosas\", \"Rosas\",\"1990-05-19\", \"F\");";
                        Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                        statement.executeUpdate(query);
                        System.out.println("A new user was inserted successfully!");
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private static java.sql.Date convert(java.util.Date fecha) {
        java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
        return fechaSQL;
    }
}