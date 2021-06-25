package conection;

import java.sql.*;

public class ConnectToRDS {
    Statement statement;
    ResultSet resultSet;
    String output;


    // Librería de RDS
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


    public String readUser(String idUser){
        String SQL = "select * from personas where persona_id = " + idUser;
        try {
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            resultSet = statement.executeQuery(SQL);
            if (resultSet.next()) {
                output =
                        "\"Usuario Borrado - ID\": " + idUser + "\""
                                + " , \" Nombre \" :\" " + resultSet.getString("persona_nombre") + "\""
                                + " , \" Apellido Paterno\" :\" " + resultSet.getString("persona_apaterno") + "\""
                                + " , \" Apellido Materno\" :\" " +  resultSet.getString("persona_amaterno") + "\""
                                + " , \" Fecha de Nacimiento\" :\" " +  resultSet.getString("persona_fecha_nacimiento") + "\""
                                + " , \" Sexo:\" :\" " +  resultSet.getString("persona_sexo") + "\""
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

    public String deleteUser(String idUser){
        String queryD = "delete from personas where persona_id = " + idUser;
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
