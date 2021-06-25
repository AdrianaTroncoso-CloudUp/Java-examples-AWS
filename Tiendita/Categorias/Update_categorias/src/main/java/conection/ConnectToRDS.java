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
    // Ruta de nuestra base de datos
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


    public String updateCategoria(String categoriaId, String categoriaNombre, String categoriaDescripcion, String categoriaActivo){
        String query = "update categorias set categoria_nombre = \""+categoriaNombre+"\", categoria_descripcion= \""+categoriaDescripcion+"\" , categoria_activo = \""+categoriaActivo+"\" where categoria_id = \""+categoriaId+"\";";
        try {
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            statement.executeUpdate(query);
            String output =
                    "\" Categoría Editada - ID\" :\" " + categoriaId + "\""
                            + " , \" Nombre\" :\" " + categoriaNombre + "\""
                            + " , \" Descripcion\" :\" " + categoriaDescripcion + "\""
                            + " , \" Categoría activa\" :\" " + categoriaActivo + "\"";

            return output;

        } catch (SQLException e){
            System.out.println("Error" +  e.getMessage());
            e.printStackTrace();
            output = output + " " + e;
        }
        return output;
    }





}
