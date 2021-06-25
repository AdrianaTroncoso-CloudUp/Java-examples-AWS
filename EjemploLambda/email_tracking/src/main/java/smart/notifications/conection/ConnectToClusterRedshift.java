package smart.notifications.conection;

import java.sql.*;

public class ConnectToClusterRedshift {

    // Librería de Redshift
    static final String driver = "com.amazon.redshift.jdbc4.Driver";
    // Nombre de la base de datos
    static final String database = "smart_notifications_data";
    // Host
    static final String hostname = "smartnotification-rs.co0xz1hlasft.us-east-1.redshift.amazonaws.com";
    // Puerto
    static final String port = "5464";
    // Ruta de nuestra base de datos (desactivamos el uso de SSL con "?useSSL=false")
    static final String url = "jdbc:redshift://" + hostname + ":" + port + "/" + database;
    // Nombre de usuario
    static final String username = "adminuser";
    // Clave de usuario
    static final String password = "AdminUser2018.";

    public static Connection conn = null;

    public ConnectToClusterRedshift() {
        try {
            Class.forName(driver);
            ConnectToClusterRedshift.conn = (Connection) DriverManager.getConnection(url, username, password);
            System.out.println("YA REALICE CONEXION A BD ...");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error catch: " +  e.getMessage());
        }
    }

    public long getDateId(String timeMx){
        return getSelect("SELECT date_id from dates WHERE date_time='" + timeMx + "'","date_id");
    }

    public long getShippingId(String messageId, long dataId){
        return getSelect("SELECT s_id from shippings WHERE s_message_id='" + messageId + "' AND date_id=" + dataId,"s_id");
    }

    public long ifThereShippingRecord(String messageId, String eventName){
        //return getSelect("SELECT s_id from shippings WHERE s_message_id='" + messageId + "'","s_id");
        return getSelect("SELECT s_id from shippings JOIN events ON shippings.s_id = events.shipping_id JOIN event_name ON events.event_name_id = event_name.eventn_id WHERE s_message_id='" + messageId + "' AND eventn_name = '" + eventName + "'","s_id");
    }

    public long getEventName(String name){
        return getSelect("SELECT eventn_id from event_name WHERE eventn_name='" + name + "'","eventn_id");
    }

    public long getTypeShipping(String type){
        return getSelect("SELECT ts_id from type_shipping WHERE ts_name='" + type + "'","ts_id");
    }

    private long getSelect(String SQL, String attr){
        ResultSet rs = null;
        long idReferenced = 0;
        Statement stmt = null;
        try {
            stmt = ConnectToClusterRedshift.conn.createStatement();
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                idReferenced = rs.getLong(attr);
            }

            rs.close();
        } catch (SQLException e){
            System.out.println("Error catch: " +  e.getMessage());
            e.printStackTrace();
        } finally{
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(Exception ex){
                System.out.println("Error finally_1: " +  ex.getMessage());
            }
        }

        return idReferenced;
    }

    public int setShipping(String sMessageId, long typeShippingId, long dateId, String sTransmiter, String sReceptor, String sSourceIp){

        int rowsAffected = 0;
        PreparedStatement pstm = null;

        String SQL = "INSERT INTO shippings(s_message_id, type_shipping_id, date_id, s_transmiter, s_receptor, s_source_ip) VALUES(?, ?, ?, ?, ?, ?)";
        try {
            pstm = ConnectToClusterRedshift.conn.prepareStatement(SQL);
            pstm.setString(1, sMessageId);
            pstm.setLong(2, typeShippingId);
            pstm.setLong(3, dateId);
            pstm.setString(4, sTransmiter);
            pstm.setString(5, sReceptor);
            pstm.setString(6, sSourceIp);

            rowsAffected = pstm.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Creating shipping failed, no rows affected.");
            }
        } catch (SQLException e){
            System.out.println("Error" +  e.getMessage());
            e.printStackTrace();
        }finally {
            try{
                if(pstm!=null) {
                    pstm.close();
                }
            }catch(Exception ex){
                System.out.println("Error finally: " +  ex.getMessage());
            }
        }

        return rowsAffected;
    }

    public int setDate(String dateCal, String dateCalAws, String dateDay, long dateWeek, String dateMonth, long dateYear, String dateTime){

        int rowsAffected = 0;
        PreparedStatement pstm = null;

        String SQL = "INSERT INTO dates(date_cal, date_cal_aws, date_day, date_week, date_month, date_year, date_time) VALUES(?, ?, ?, ?, ?, ?, ?)";
        try {
            pstm = ConnectToClusterRedshift.conn.prepareStatement(SQL);
            pstm.setString(1, dateCal);
            pstm.setString(2, dateCalAws);
            pstm.setString(3, dateDay);
            pstm.setLong(4, dateWeek);
            pstm.setString(5, dateMonth);
            pstm.setLong(6, dateYear);
            pstm.setString(7, dateTime);


            rowsAffected = pstm.executeUpdate();

            if (rowsAffected == 0) {
                throw new SQLException("Creating shipping failed, no rows affected.");
            }
        } catch (SQLException e){
            System.out.println("Error" +  e.getMessage());
            e.printStackTrace();
        }finally {
            try{
                if(pstm!=null) {
                    pstm.close();
                }
            }catch(Exception ex){
                System.out.println("Error finally: " +  ex.getMessage());
            }
        }

        return rowsAffected;
    }

    public int setEvent(long dateId, long eventNameId, long shippingId, String eventExtraDetails){

        int rowsAffected = 0;
        PreparedStatement pstm = null;
        String SQL = "";
        if(eventExtraDetails != null)
            SQL = "INSERT INTO events(date_id, event_name_id, shipping_id, event_extra_details) VALUES(?, ?, ?, ?)";
        else
            SQL = "INSERT INTO events(date_id, event_name_id, shipping_id) VALUES(?, ?, ?)";

        try {
            pstm = ConnectToClusterRedshift.conn.prepareStatement(SQL);
            pstm.setLong(1, dateId);
            pstm.setLong(2, eventNameId);
            pstm.setLong(3, shippingId);
            if(eventExtraDetails != null)
                pstm.setString(4, eventExtraDetails);

            rowsAffected = pstm.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Creating shipping failed, no rows affected.");
            }
        } catch (SQLException e){
            System.out.println("Error" +  e.getMessage());
            e.printStackTrace();
        }finally {
            try{
                if(pstm!=null) {
                    pstm.close();
                }
            }catch(Exception ex){
                System.out.println("Error finally: " +  ex.getMessage());
            }
        }

        return rowsAffected;
    }
}
