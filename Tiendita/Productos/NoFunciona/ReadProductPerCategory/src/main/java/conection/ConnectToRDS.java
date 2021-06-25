package conection;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.mx.Response;


import java.sql.*;
import java.util.List;

public class ConnectToRDS {
    Statement statement;
    ResultSet resultSet;
    HashMap <String,String> data;
    HashMap<String, HashMap> outerMap = new HashMap<String, HashMap>();
    int categoriaActiva = 0;
    List<HashMap> values = new ArrayList<HashMap>();

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

//JSONObject
    public Response readProducto(String idCategoria){
        String SQL =
            "SELECT categorias.categoria_activo, categorias.categoria_nombre, productos.producto_id, productos.producto_nombre,"
            + "productos.producto_descripcion, productos.producto_precio, productos.producto_categoria_id"
            + "FROM productos INNER JOIN categorias ON productos.producto_categoria_id=categorias.categoria_id"
            + "WHERE productos.producto_categoria_id =" + idCategoria;

        try {
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                categoriaActiva = Integer.parseInt(resultSet.getString("categoria_activo"));
                if(categoriaActiva == 1) {
                    String productoId = resultSet.getString("producto_id");
                    data.put("producto_id", productoId);
                    data.put("producto_nombre", resultSet.getString("producto_nombre"));
                    data.put("producto_descripcion", resultSet.getString("producto_descripcion"));
                    data.put("producto_precio", resultSet.getString("producto_precio"));

                    outerMap.put(productoId,data);

                }else {
                    data.put("Error:", "La categoria de estos productos no esta activa.");
                    System.out.println("Error: La categoria de estos productos no esta activa.");
                    outerMap.put("Errores",data);
                    break;
                }
            }
            List<HashMap> values = new ArrayList<>(outerMap.values());
            System.out.print(values);

            return new Response("success",200,values,new Date()+"");


        } catch (SQLException e){
            System.out.println("Error" +  e.getMessage());
            e.printStackTrace();
            data.put("Error", e.getMessage());
            List<HashMap> values = new ArrayList<>(outerMap.values());
            return new Response("dad request",400,values,new Date()+"");

        }

    }
}
