package com.mx;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.sql.*;
import java.util.Map;

public class Delete implements RequestHandler<Map<String,String>, String> {
    Statement statement;
    ResultSet resultSet;
    String output;
    String newOutput;

    @Override
    public String handleRequest(Map<String, String> event, Context context) {
        String resultado = "Hola";
        // String abc=database.database(input);
        try {
            Class.forName("com.mysql.jdbc.Driver");

//            Connection con = DriverManager.getConnection(
//                    "jdbc:mysql://Endpoint/DB_Name", "User_Name", "password");

            Connection con = (Connection) DriverManager.getConnection(
                    "jdbc:mysql://database-1.cmhhj6fqpw4w.us-east-1.rds.amazonaws.com:3306/Gente?useSSL=false", "adminFeliz", "passwordFeliz");

            context.getLogger().log("Test Started");
            String query = "select * from personas where persona_id = " + event.get("id");

            Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                output =
                        "\" Usuario Borrado  ID\": " + event.get("id") + "\""
                                + " , \" Nombre \" :\" " + resultSet.getString("persona_nombre") + "\""
                                + " , \" Apellido Paterno\" :\" " + resultSet.getString("persona_apaterno") + "\""
                                + " , \" Apellido Materno\" :\" " + resultSet.getString("persona_amaterno") + "\""
                                + " , \" Fecha de Nacimiento\" :\" " + resultSet.getString("persona_fecha_nacimiento") + "\""
                                + " , \" Sexo:\" :\" " + resultSet.getString("persona_sexo") + "\""
                ;
            }
            output = "{" + output + "}";
            resultado = output;
            context.getLogger().log(output);


            String queryD = "delete from personas where persona_id = " + event.get("id");
            statement.executeUpdate(queryD);
            context.getLogger().log("Borrando...    queryDelete: " + queryD);

            return output;
        } catch (SQLException e) {
            e.printStackTrace();
            resultado = resultado + " " + e;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            resultado = resultado + " " + e;
        }
        return resultado;
    }

}
