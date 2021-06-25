package smart.notifications;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SNSEvent;
import smart.notifications.conection.ConnectToRDS;
import smart.notifications.util.Fechas;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.stream.JsonParsingException;
import java.io.StringReader;
import java.util.Date;
import java.util.UUID;

public class Tracking implements RequestHandler<SNSEvent, Object> {

    private ConnectToClusterRedshift ConnectToRDS = null;

    public Object handleRequest(SNSEvent request, Context context) {

        // CICLAMOS EL NODO RECORDS
        int sizeRecordList = request.getRecords().size();
        for(int i = 0; i < sizeRecordList; i++){
            String message = request.getRecords().get(i).getSNS().getMessage();
            context.getLogger().log("Message[" + i +  "] : " + message);

            try {
                // CREAMOS UN JSON_READER PARA LEER CADENA EN JSON, CONVERTIR EL OBJECT
                JsonReader jsonReader = Json.createReader(new StringReader(message));
                // OBTENEMOS UN OBJETO DEL JSON PARA PODER SACAR LOS DATOS
                JsonObject jsonObject = jsonReader.readObject();

                if(jsonObject.containsKey("eventType")) {

                    System.out.println("INICIANDO CONEXION BD ...");
                    connectToRDS = new ConnectToRDS();

                    String eventType = jsonObject.getString("eventType");
                    switch (eventType) {
                        case "Bounce":
                            context.getLogger().log("Event type: Bounce");
                            getDataEMAIL(jsonObject, "Bounce");
                            break;
                        case "Complaint":
                            context.getLogger().log("Event type: Complaint");
                            getDataEMAIL(jsonObject, "Complaint");
                            break;
                        case "Delivery":
                            context.getLogger().log("Event type: Delivery");
                            getDataEMAIL(jsonObject, "Delivery");
                            break;
                        case "Send":
                            context.getLogger().log("Event type: Send");
                            getDataEMAIL(jsonObject, "Send");
                            break;
                        case "Reject":
                            context.getLogger().log("Event type: Reject");
                            getDataEMAIL(jsonObject, "Reject");
                            break;
                        case "Open":
                            context.getLogger().log("Event type: Open");
                            getDataEvent(jsonObject, "Open");
                            break;
                        case "Click":
                            context.getLogger().log("Event type: Click");
                            getDataEvent(jsonObject, "Click");
                            break;
                        case "Rendering Failure":
                            context.getLogger().log("Event type: Rendering Failure");
                            getDataEMAIL(jsonObject, "Rendering Failure");
                            break;
                        default:
                            context.getLogger().log("Event type: No conocido");
                    }
                } else {
                    context.getLogger().log("No contiene la llave: eventType");
                }
            } catch (JsonParsingException e){
                context.getLogger().log("Error en transformar cadena a objeto JSON!.");
            }
        }

        try{
            if(ConnectToClusterRedshift.conn!=null) {
                ConnectToClusterRedshift.conn.close();
            }
        }catch(Exception ex){
            System.out.println("Error en cerrar conexion BD: " +  ex.getMessage());
        }
        return null;
    }

    private void getDataEMAIL(JsonObject jsonObject, String eventType){

        // OBTENEMOS EL MESSAGES ID DE LA PETICION
        String messageId = jsonObject.getJsonObject("mail").getString("messageId");
        // VALIDAMOS SI EXISTE EL SHIPPING CON EL MESSAGE_ID
        long shippingIdExists = connectToClusterRedshift.ifThereShippingRecord(messageId, eventType);

        // SI NO EXISTE
        if(shippingIdExists == 0) {

            // CONSULTAMOS EL TIPO DE CAMPAIGN
            long typeShippingId = connectToClusterRedshift.getTypeShipping("EMAIL");

            // OBTENEMOS EL TIMESTAMP DE LA PETICION
            String timestamp = jsonObject.getJsonObject("mail").getString("timestamp");

            // PARSEAMOS LA FECHA
            String fechaParseada = Fechas.dateFormater(timestamp, "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

            // SETEAMOS LA FECHA EN OBJECTO TIPO DATE
            Date date = Fechas.parseStringToDate(fechaParseada);

            // OBTENEMOS EL TIMESTAMP PARA MEXICO
            long timeMx = Fechas.getDateWithMinusHours(date, 5) / 1000L;
            UUID uuid = UUID.randomUUID();
            String timeMxR = timeMx + "_" + uuid.toString().replace("-", "");

            // INSERTAMOS EN DATE
            int rowsAffectedDate = connectToClusterRedshift.setDate(
                    Fechas.convertUnixDate(timeMx),
                    Fechas.convertUnixDate(date.getTime() / 1000L),
                    Fechas.getDayOfMonth(date) + "",
                    Fechas.getNumWeek(date),
                    Fechas.getNameMonth(date),
                    Fechas.getYear(date),
                    timeMxR
            );

            // VALIDAMOS CUANTOS INSERT FUERON EJECUTADOS
            if (rowsAffectedDate > 0) {

                // OBTENEMOS EL ID DE DATE ANTERIORMENTE INSERTADO
                long dateId = connectToClusterRedshift.getDateId(timeMxR);

                // OBTENEMOS EL SOURCE DE LA PETICION
                String source = jsonObject.getJsonObject("mail").getString("source");

                // OBTENEMOS EL ARRAY DE DESTINATARIOS DE LA PETICION
                JsonArray destination = jsonObject.getJsonObject("mail").getJsonArray("destination");

                // OBTENEMOS LA IP DE LA PETICION
                JsonArray sesSourceIp = null;
                String IP = "";
                if (!eventType.equals("Rendering Failure")) {
                    sesSourceIp = jsonObject.getJsonObject("mail").getJsonObject("tags").getJsonArray("ses:source-ip");
                    IP = sesSourceIp.getString(0);
                }

                // INSERTAMOS EN SHIPPING
                int rowsAffectedShipping = connectToClusterRedshift.setShipping(
                        messageId,
                        typeShippingId,
                        dateId,
                        source,
                        destination.getString(0),
                        IP
                );

                // VALIDAMOS SI EL INSERT FUE EXITOSO EN SHIPPING
                if (rowsAffectedShipping > 0) {

                    // OBTENER EL ID DE SHIPPING ANTERIORMENTE INSERTADA
                    long shippingId = connectToClusterRedshift.getShippingId(messageId, dateId);

                    // CONSULTAMOS EL NOMBRE DEL EVENTO CON VALOR "eventType"
                    long eventNameId = connectToClusterRedshift.getEventName(eventType);

                    // OBTENEMOS LOS DATOS EXTRA
                    if (eventType.equals("Rendering Failure"))
                        eventType = "failure";
                    // OBTENEMOS EL PARAMETRO PARA GUARDAR EN EXTRA_MESSAGE
                    JsonObject extraMessage = jsonObject.getJsonObject(eventType.toLowerCase());

                    // COMO ES SHIPPING "eventType" GUARDAMOS EN EVENT
                    connectToClusterRedshift.setEvent(dateId, eventNameId, shippingId, extraMessage + "");

                } else {
                    System.out.println("No se puede obtener Id de shipping...");
                }
            } else {
                System.out.println("No se puede obtener Id de date...");
            }
        } else {
            System.out.println("Existe el shipping ID no se hace nada" + messageId);
        }
    }

    private void getDataEvent(JsonObject jsonObject, String event){

        // OBTENEMOS EL MESSAGES ID DE LA PETICION
        String messageId = jsonObject.getJsonObject("mail").getString("messageId");

        // CONSULTAMOS EL TIPO DE CAMPAIGN
        long typeShippingId = connectToClusterRedshift.getTypeShipping("EMAIL");

        // VALIDAMOS SI EXISTE EL SHIPPING CON EL MESSAGE_ID
        long shippingIdExists = connectToClusterRedshift.ifThereShippingRecord(messageId, event);

        // SI NO EXISTE
        if(shippingIdExists == 0){

            // OBTENGO ID CON EL NOMBRE DE EVENTO
            long eventId = connectToClusterRedshift.getEventName(event);

            String timestamp = "";
            // OBTENEMOS EL TIMESTAMP DE LA PETICION
            if(event.equalsIgnoreCase("Open"))
                timestamp = jsonObject.getJsonObject("open").getString("timestamp");
            if(event.equalsIgnoreCase("Click"))
                timestamp = jsonObject.getJsonObject("click").getString("timestamp");

            // PARSEAMOS LA FECHA
            String fechaParseada = Fechas.dateFormater(timestamp, "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

            // SETEAMOS LA FECHA EN OBJECTO TIPO DATE
            Date date = Fechas.parseStringToDate(fechaParseada);

            // OBTENEMOS EL TIMESTAMP PARA MEXICO
            long timeMx = Fechas.getDateWithMinusHours(date, 5)/1000L;
            UUID uuid = UUID.randomUUID();
            String timeMxR = timeMx + "_" + uuid.toString().replace("-", "");

            // INSERTAMOS EN DATE
            int rowsAffectedDate = connectToClusterRedshift.setDate(
                    Fechas.convertUnixDate(timeMx),
                    Fechas.convertUnixDate(date.getTime()/1000L),
                    Fechas.getDayOfMonth(date) + "",
                    Fechas.getNumWeek(date),
                    Fechas.getNameMonth(date),
                    Fechas.getYear(date),
                    timeMxR
            );

            if(rowsAffectedDate > 0){
                // OBTENEMOS EL ID DE DATE ANTERIORMENTE INSERTADO
                long dateId = connectToClusterRedshift.getDateId(timeMxR);

                // #########################################################################################################
                // OBTENEMOS EL SOURCE DE LA PETICION
                String source = jsonObject.getJsonObject("mail").getString("source");

                // OBTENEMOS EL ARRAY DE DESTINATARIOS DE LA PETICION
                JsonArray destination = jsonObject.getJsonObject("mail").getJsonArray("destination");

                // OBTENEMOS LA IP DE LA PETICION
                JsonArray sesSourceIp = jsonObject.getJsonObject("mail").getJsonObject("tags").getJsonArray("ses:source-ip");
                String IP = sesSourceIp.getString(0);

                // INSERTAMOS EN SHIPPING
                int rowsAffectedShipping = connectToClusterRedshift.setShipping(
                        messageId,
                        typeShippingId,
                        dateId,
                        source,
                        destination.getString(0),
                        IP
                );

                // VALIDAMOS SI EL INSERT FUE EXITOSO EN SHIPPING
                if (rowsAffectedShipping > 0) {

                    // OBTENER EL ID DE SHIPPING ANTERIORMENTE INSERTADA
                    long shippingId = connectToClusterRedshift.getShippingId(messageId, dateId);

                    // INSERTO EN TABLA EVENTS
                    if(event.equalsIgnoreCase("Open")){
                        String ip = jsonObject.getJsonObject("open").getString("ipAddress");
                        connectToClusterRedshift.setEvent(dateId, eventId, shippingId, ip);
                    }
                    if(event.equalsIgnoreCase("Click")){
                        String link = jsonObject.getJsonObject("click").getString("link");
                        String ip = jsonObject.getJsonObject("click").getString("ipAddress");
                        connectToClusterRedshift.setEvent(dateId, eventId, shippingId, link + "|" +ip);
                    }

                } else {
                    System.out.println("No se puede obtener Id de shipping...");
                }
                // #########################################################################################################
            } else {
                System.out.println("No se puede obtener Id de date...");
            }
        } else {
            System.out.println("No existe el shipping: " + messageId);
        }
    }
}
