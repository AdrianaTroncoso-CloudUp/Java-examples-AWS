package com.mx.smart.notifications;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.pinpoint.AmazonPinpoint;
import com.amazonaws.services.pinpoint.AmazonPinpointClientBuilder;
import com.amazonaws.services.pinpoint.model.AddressConfiguration;
import com.amazonaws.services.pinpoint.model.ChannelType;
import com.amazonaws.services.pinpoint.model.DirectMessageConfiguration;
import com.amazonaws.services.pinpoint.model.MessageRequest;
import com.amazonaws.services.pinpoint.model.SMSMessage;
import com.amazonaws.services.pinpoint.model.SendMessagesRequest;

import com.mx.smart.notifications.connection.ConnectionMySQL;
import com.mx.smart.notifications.util.Request;
import com.mx.smart.notifications.util.Response;

import java.sql.*;
import java.util.HashMap;
import java.io.IOException;
import java.util.Map;

public class Main implements RequestHandler<Request, Response> {

    private LambdaLogger lambdaLogger;

    public Response handleRequest(Request request, Context context) {

        lambdaLogger = context.getLogger();
        // IMPRIMIMOS TODO EL MENSAJE
        lambdaLogger.log("Message: " + request.toString());



        ConnectionMySQL connectionMySQL = new ConnectionMySQL(
                System.getenv("DATABASE"),
                System.getenv("HOSTNAME"),
                System.getenv("PORT"),
                System.getenv("USERNAME"),
                System.getenv("PASSWORD")
        );

        ResultSet rs = null;

        String sSQL =   "SELECT * FROM team";

        try(Connection conn = connectionMySQL.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sSQL);
            ) {

            rs = pstm.executeQuery();
            String names = "";
            while (rs.next()) {
                names += " - " + rs.getString("team_name");
                lambdaLogger.log("Nombre: " + names);
            }

            pstm.close();
            return new Response("Nombres: " + names,"Success");


        } catch(SQLException e){
            lambdaLogger.log("ERROR: " + e.getMessage());
            return new Response(e.getMessage(),"Error");
        } finally {
            try {
                if(rs != null)  rs.close();
            } catch (SQLException e) {
                lambdaLogger.log("ERROR: " + e.getMessage());
            }
        }

    }

    private void sendMessage(String destinationNumber, String message, String messageType){

        String region = System.getenv("REGION");
        String originationNumber = System.getenv("ORIGINATION_NUMBER");
        String appId = System.getenv("APP_ID");
        String registeredKeyword = System.getenv("REGISTERED_KEYWORD");
        String senderId = System.getenv("SENDER_ID");

        try {
            Map<String,AddressConfiguration> addressMap =
                    new HashMap<String,AddressConfiguration>();

            addressMap.put(destinationNumber, new AddressConfiguration()
                    .withChannelType(ChannelType.SMS));

            AmazonPinpoint client = AmazonPinpointClientBuilder.standard()
                    .withRegion(region).build();

            SendMessagesRequest request = new SendMessagesRequest()
                    .withApplicationId(appId)
                    .withMessageRequest(new MessageRequest()
                            .withAddresses(addressMap)
                            .withMessageConfiguration(new DirectMessageConfiguration()
                                    .withSMSMessage(new SMSMessage()
                                            .withBody(message)
                                            .withMessageType(messageType)
                                            .withOriginationNumber(originationNumber)
                                            .withSenderId(senderId)
                                            .withKeyword(registeredKeyword)
                                    )
                            )
                    );
            lambdaLogger.log("Sending message...");
            client.sendMessages(request);
            lambdaLogger.log("Message sent!");
        } catch (Exception ex) {
            lambdaLogger.log("ERROR: " + "The message wasn't sent. Error message: " + ex.getMessage());
        }
    }
}
