package com.mx.smart.notifications.connection;

import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMySQL {

    private String driver = "com.mysql.jdbc.Driver";
    private Connection conn = null;
    public String database;
    public String hostname;
    public String port;
    public String username;
    public String password;

    public ConnectionMySQL(String database, String hostname, String port, String username, String password) {
        this.database = database;
        this.hostname = hostname;
        this.port = port;
        this.username = username;
        this.password = password;
    }


    public Connection getConnection() {

        String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?useSSL=false";

        try {
            Class.forName(driver);
            conn = (Connection) DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error: " +  e.getMessage());
        }

        return conn;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
