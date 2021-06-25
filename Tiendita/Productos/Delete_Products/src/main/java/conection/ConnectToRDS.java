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


    public String readProducto(String idProducto){
        String SQL = "select * from productos where producto_id = " + idProducto;
        try {
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            resultSet = statement.executeQuery(SQL);
            if (resultSet.next()) {
                output =
                    "\"Producto Borrado - ID\": " + idProducto + "\""
                    + " , \" Nombre \" :\" " + resultSet.getString("producto_nombre") + "\""
                    + " , \" Descripcion\" :\" " + resultSet.getString("producto_descripcion") + "\""
                    + " , \" Precio\" :\" " +  resultSet.getString("producto_precio") + "\""
                    + " , \" Id de Categoria\" :\" " +  resultSet.getString("producto_categoria_id") + "\""
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

    public String deleteUser(String idProducto){
        String queryD = "delete from productos where producto_id = " + idProducto;
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
