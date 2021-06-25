package conection;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;

public class ConnectToRDS {
    Statement statement;
    ResultSet resultSet;
//    String output;
    HashMap<String, HashMap> output = new HashMap<String, HashMap>();
    HashMap<String,String> data = new HashMap<String, String>();
    HashMap<String, HashMap> outerMap = new HashMap<String, HashMap>();
//    List<HashMap> readProducto = new HashMap<String, HashMap>();
    List<HashMap> readProducto = new ArrayList<>();
    int categoriaActiva = 0;


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


//producto_nombre, producto_descripcion, producto_precio, producto_categoria_id

    public List<HashMap> readProducto(String idProducto){
        String SQL =
                "SELECT categorias.categoria_activo, categorias.categoria_nombre, producto_id, producto_nombre, producto_descripcion, producto_precio, producto_categoria_id FROM productos INNER JOIN categorias ON productos.producto_categoria_id=categorias.categoria_id WHERE producto_categoria_id =" + idProducto;
        try {
            System.out.println("Intentando...");
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            resultSet = statement.executeQuery(SQL);
            System.out.println("Ya se envio query");
            String productoId = "";
            while (resultSet.next()) {
                categoriaActiva = Integer.parseInt(resultSet.getString("categoria_activo"));
                if(categoriaActiva == 1) {
                    productoId = resultSet.getString("producto_id");
                    data.put("producto_id", productoId);
                    data.put("producto_nombre", resultSet.getString("producto_nombre"));
                    data.put("producto_descripcion", resultSet.getString("producto_descripcion"));
                    data.put("producto_precio", resultSet.getString("producto_precio"));
//                    data.clear();
                    outerMap.put(productoId,data);

                }else {
                    data.put("Error:", "La categoria de estos productos no esta activa.");
//                    output = "Error: La categoria de estos productos no esta activa.";
                    outerMap.put(productoId,data);
                    break;
                }
            }
//            List<HashMap> values = new ArrayList<>(outerMap.values());
//            output = String.valueOf(values);
            output = outerMap;
            List<HashMap> values = new ArrayList<>(outerMap.values());
            System.out.println("Output: "+output);
            return values;

        } catch (SQLException e){
            System.out.println("Error" +  e.getMessage());
            e.printStackTrace();
            HashMap<String,String> error = new HashMap<String, String>();
            error.put("Error:", ""+e+".");
//            output = output + " " + e;
            HashMap<String,HashMap> data1 = new HashMap<String, HashMap>();
            data1.put("Error", error);
            List<HashMap> values1 = new ArrayList<>(data1.values());
            return values1;
        }

    }
}
