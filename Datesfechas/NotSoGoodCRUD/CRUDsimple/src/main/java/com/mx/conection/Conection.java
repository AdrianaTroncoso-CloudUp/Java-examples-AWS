package com.mx.conection;

import java.sql.*;

public class Conection {
    // Librería de sql
    static final String driver = "com.mysql.jdbc.Driver";
    // Nombre de la base de datos
    static final String database = "Gente";
    // Host
    static final String hostname = "database-1.cmhhj6fqpw4w.us-east-1.rds.amazonaws.com";
    // Puerto
    static final String port = "3306";
    // Ruta de nuestra base de datos (desactivamos el uso de SSL con "?useSSL=false")
    static final String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database;
    // Nombre de usuario
    static final String username = "adminFeliz";
    // Clave de usuario
    static final String password = "passwordFeliz.";

    public static java.sql.Connection conn = null;

    public ConnectToRDS(){
        try{
            Class.forName(driver);
            Connection con = (Connection) DriverManager.getConnection(url, username, password);
            System.out.println("YA REALICE CONEXION A BD ...");
        } catch (ClassNotFoundException | SQLException e){
            System.out.println("Error catch: " +  e.getMessage());
        }
    }

}
