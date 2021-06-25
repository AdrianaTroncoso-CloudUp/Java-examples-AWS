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

import com.mx.smart.notifications.util.Request;
import com.mx.smart.notifications.util.Response;

import java.sql.*;
import java.util.HashMap;
import java.io.IOException;
import java.util.Map;

public class Main implements RequestHandler<Request, Response> {

//    private LambdaLogger lambdaLogger;
//
//    public Response handleRequest(Request request, Context context) {
//
//        lambdaLogger = context.getLogger();
//        // IMPRIMIMOS TODO EL MENSAJE
//        lambdaLogger.log("Message: " + request.toString());
//
//    }

}
