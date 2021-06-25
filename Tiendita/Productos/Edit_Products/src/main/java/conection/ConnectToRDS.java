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


    public String updateProducto(String idProducto, String nombre, String descripcion, String precio, String idCategoria){
        String query = "update productos set producto_nombre = \""+nombre+"\", producto_descripcion= \""+descripcion+"\" , producto_precio = \""+precio+"\", producto_categoria_id = \""+idCategoria+"\" where producto_id = \""+idProducto+"\";";
        try {
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            statement.executeUpdate(query);
            String output =
                    "\" Producto editado -  ID \" :\" " + idProducto + "\""
                            + " , \" Nombre \" :\" " + nombre + "\""
                            + " , \" Descripcion\" :\" " + descripcion + "\""
                            + " , \" Precio\" :\" " + precio + "\""
                            + " , \" idCategoria\" :\" " + idCategoria + "\"";
            return output;

        } catch (SQLException e){
            System.out.println("Error" +  e.getMessage());
            e.printStackTrace();
            output = output + " " + e;
        }
        return output;
    }





}
