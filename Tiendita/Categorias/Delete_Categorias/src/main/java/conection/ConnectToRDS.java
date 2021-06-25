package conection;

import java.sql.*;

public class ConnectToRDS {
    Statement statement;
    ResultSet resultSet;
    String output;


    // Librería de RDS
    static final String driver = "com.mysql.jdbc.Driver";
    // Nombre de la base de datos
    static final String database = "Tiendita";
    // Host
    static final String hostname = "database-1.cmhhj6fqpw4w.us-east-1.rds.amazonaws.com";
    // Puerto
    static final String port = "3306";
    // Ruta de nuestra base de datos (desactivamos el uso de SSL con "?useSSL=false")
    static final String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database;
    // Nombre de usuario
    static final String username = "adminFeliz";
    // Clave de usuario
    static final String password = "passwordFeliz";

    public static Connection conn = null;

    public ConnectToRDS() {
        try {
            Class.forName(driver);
            ConnectToRDS.conn = (Connection) DriverManager.getConnection(url, username, password);
            System.out.println("Conexion a BD exitosa :)");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error catch: " +  e.getMessage());
        }
    }


    public String readCategorias(String idCategorias){
        String SQL = "select * from categorias where categoria_id = " + idCategorias;
        try {
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            resultSet = statement.executeQuery(SQL);
            if (resultSet.next()) {
                output =
                    "\"Categoria Borrada - ID\": " + idCategorias + "\""
                    + " , \" Nombre \" :\" " + resultSet.getString("categoria_nombre") + "\""
                    + " , \" Descripcion\" :\" " + resultSet.getString("categoria_descripcion") + "\""
                    + " , \" Categoría activa\" :\" " +  resultSet.getString("categoria_activo") + "\""
                    ;
            }
            System.out.println(output);
            return output;

        } catch (SQLException e){
            System.out.println("Error" +  e.getMessage());
            e.printStackTrace();
            output = output + " " + e;
        }
        return output;
    }

    public String deleteCategorias(String idCategorias){
        String queryD = "delete from categorias where categoria_id = " + idCategorias;
        try {
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            statement.executeUpdate(queryD);
            output =" Operación exitosa.";
            return output;

        } catch (SQLException e){
            System.out.println("Error" +  e.getMessage());
            e.printStackTrace();
            output = output + " " + e;
        }
        return output;
    }




}
