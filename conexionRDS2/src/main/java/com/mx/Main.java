package com.mx;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.sql.*;

public class Main implements RequestHandler<String, String> {
    Statement statement;
    ResultSet resultSet;

    @Override
    public String handleRequest(String input, Context context) {
        String resultado = "Hola";
        // String abc=database.database(input);
        try {
            Class.forName("com.mysql.jdbc.Driver");

//            Connection con = DriverManager.getConnection(
//                    "jdbc:mysql://Endpoint/DB_Name", "User_Name", "password");

            Connection con = (Connection) DriverManager.getConnection(
                    "jdbc:mysql://database-1.cmhhj6fqpw4w.us-east-1.rds.amazonaws.com:3306/Gente?useSSL=false", "adminFeliz", "passwordFeliz");

            context.getLogger().log("Test Started");
            String query = "select * from personas where persona_id = "+ input;
            Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                String output = "Hello, " + resultSet.getString("persona_nombre") + "!";
                resultado = output;
                System.out.println(resultSet.getString("persona_id") + "Console: Hello " + resultSet.getString("persona_nombre") + " !");
                return output;
            }
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
