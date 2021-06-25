//package com.mx;
//
//import java.sql.*;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Map;
//
//import com.amazonaws.services.lambda.runtime.Context;
//import com.amazonaws.services.lambda.runtime.RequestHandler;
//
//import java.sql.*;
//import java.util.Date;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.util.Map;
//import java.util.Arrays;
//
//public class postPinpoint implements RequestHandler<Map<String,String>, String> {
//    Statement statement;
//    ResultSet resultSet;
//    ResultSet resultSet1;
//
//    Date cumple;
//    String cumple1;
//    Date today;
//    Date parsed;
//    String resultado = "Hola";
//    int cuentaPersona = 0;
//
//    @Override
//    public String handleRequest() {
//
//        try {
//                    Class.forName("com.mysql.jdbc.Driver");
//                    Connection con = (Connection) DriverManager.getConnection(
//                            "jdbc:mysql://database-1.cmhhj6fqpw4w.us-east-1.rds.amazonaws.com:3306/Gente?useSSL=false", "adminFeliz", "passwordFeliz");
//                    context.getLogger().log("Test Started");
//
//                    String cuenta = "select count(*) as cuenta from Gente.personas";
//                    Statement statement1 = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
//                    resultSet1 = statement1.executeQuery(cuenta);
//                    if (resultSet1.next()) {
//                        cuentaPersona = resultSet1.getInt("cuenta");
//                        cuentaPersona = cuentaPersona + 1;
//                    }
//                    context.getLogger().log("Número de personas: " + cuenta + " ID nuevo:" + cuentaPersona);
//                    System.out.println("nombre: " + persona_nombre + ", apaterno:" + persona_apaterno + ", amaterno:" + persona_amaterno +
//                            ", fecha_nacimiento:" + sqlDateCumple + ", sexo:" + persona_sexo + ".");
//                    String query = "insert into personas (persona_nombre, persona_apaterno, persona_amaterno, persona_fecha_nacimiento , persona_sexo) \n" +
//                            "values(\""+persona_nombre+"\", \""+persona_apaterno+"\", \""+persona_amaterno+"\",\""+sqlDateCumple+"\", \""+persona_sexo+"\");";
//                    Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
//
//                    statement.executeUpdate(query);
//                    context.getLogger().log("query: " + query);
//                    String output =
//                            "\" Nuevo usuario - Nombre \" :\" " + persona_nombre + "\""
//                                    + " , \" Apellido Paterno\" :\" " + persona_apaterno + "\""
//                                    + " , \" Apellido Materno\" :\" " + persona_amaterno + "\""
//                                    + " , \" Fecha de Nacimiento\" :\" " + cumple + "\""
//                                    + " , \" Sexo:\" :\" " + persona_sexo + "\"";
//                    output = "{" + output + "}";
//                    resultado = output;
//                    context.getLogger().log(output);
//
//                } catch (ClassNotFoundException e) {
//                    e.printStackTrace();
//                    resultado = resultado + " " + e;
//                } catch (SQLException throwables) {
//                    throwables.printStackTrace();
//                    resultado = resultado + " " +throwables;
//                }
//
//                return resultado;
//            }
//
//    }
//
//
//}
