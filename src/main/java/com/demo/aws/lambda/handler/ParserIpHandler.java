/**
 * Alipay.com Inc. Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.demo.aws.lambda.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 *
 * @author ljliu
 * @version $Id: ParserIpHandler.java, v 0.1 2019年02月27日 下午10:36 ljliu Exp $
 */
public class ParserIpHandler implements RequestStreamHandler {

    JSONParser parser =new JSONParser();

    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        LambdaLogger logger = context.getLogger();
        logger.log("Loading Java Lambda handler of ProxyWithStream");

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        JSONObject responseJson = new JSONObject();
        responseJson.put("statusCode", "200");

        JSONObject responseBody = new JSONObject();
        responseBody.put("success",true);

        try {
            JSONObject request = (JSONObject)parser.parse(reader);

            JSONObject info=new JSONObject();
            if (request.get("headers") != null) {
                JSONObject headers = (JSONObject)request.get("headers");

                //get user ip from header check proxy
                String userIP=null;
                if (headers.get("X-Forwarded-For") != null) {
                    userIP = (String)headers.get("X-Forwarded-For");
                }
                if(userIP.length()==0 || "unknown".equals(userIP)){
                    if(headers.get("Proxy-Client-IP") !=null){
                        userIP = (String)headers.get("Proxy-Client-IP");
                    }
                }
                if(userIP.length()==0 || "unknown".equals(userIP)){
                    if(headers.get("WL-Proxy-Client-IP") !=null){
                        userIP = (String)headers.get("WL-Proxy-Client-IP");
                    }
                }
                info.put("userIP",userIP);

                //get host and parser ip
                if (headers.get("Host") != null) {
                    String host = (String)headers.get("Host");
                    InetAddress address = InetAddress.getByName(host);
                    info.put("remoteIP",address.getHostAddress());
                }

            }

            responseBody.put("input", request.toJSONString());
            responseBody.put("data", info.toJSONString());


        } catch(ParseException pex) {
            responseBody.put("data", pex);
        }

        JSONObject headerJson = new JSONObject();
        headerJson.put("x-custom-header", "this is my aws lambda demo");

        responseJson.put("headers", headerJson);
        responseJson.put("isBase64Encoded", false);
        responseJson.put("body", responseBody.toString());

        logger.log(responseJson.toJSONString());
        OutputStreamWriter writer = new OutputStreamWriter(outputStream, "UTF-8");
        writer.write(responseJson.toJSONString());
        writer.close();

    }

}