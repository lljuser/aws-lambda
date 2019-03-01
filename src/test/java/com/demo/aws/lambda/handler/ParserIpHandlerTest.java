package com.demo.aws.lambda.handler;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

/**
 * ParserIpHandler unit test and bdd test
 * because only one function and it coverge the bdd test
 */
public class ParserIpHandlerTest {
    static String REQUEST_FILE= "mockRequest.json";
    static String RESPONSE_FILE="mockResponse.json";
    /**
     * ParserIpHandler->handleRequest  parse request inputstream and get the user's ip and remote ip
     */
    @Test
    public void handleRequest() {
        boolean isSuccess = handleRequest(REQUEST_FILE);
        Assert.assertEquals(isSuccess,true);
    }

    @Test
    public void verfiyUserIP(){
        JSONParser parser =new JSONParser();
        InputStream inputStream= MockHelper.readMockRequest(RESPONSE_FILE);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String userIP=null;
        try {
            JSONObject response = (JSONObject)parser.parse(reader);
            if(response.get("body")!=null){
                String bodyStr=  (String)response.get("body");
                JSONObject jsonBody= (JSONObject)parser.parse(bodyStr);
                if(jsonBody.get("data")!=null){
                    JSONObject data= (JSONObject)jsonBody.get("data");
                    if(data.get("userIP")!=null){
                        userIP=(String)data.get("userIP");
                    }

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


        Assert.assertNotNull(userIP);
    }

    @Test
    public void handleRequestDataFormat() {
        int count=0;
        if(handleRequest("mockRequest1.json"))
            count++;

        if(handleRequest("mockRequest2.json"))
            count++;

        if(handleRequest("mockRequest3.json"))
            count++;

        if(handleRequest("mockRequest4.json"))
            count++;

        if(handleRequest("mockRequest5.json"))
            count++;

        if(handleRequest("mockRequest6.json"))
            count++;

        Assert.assertEquals(count,6);
    }


    /**
     * test branchs
     * @param fileName
     * @return
     */
    private boolean handleRequest(String fileName) {

        ParserIpHandler handler =new ParserIpHandler();

        InputStream inputStream = MockHelper.readMockRequest(fileName);
        OutputStream outputStream = MockHelper.saveMockReponse(RESPONSE_FILE);

        boolean isSuccess=true;
        try {
            handler.handleRequest(inputStream,outputStream,null);
        } catch (IOException e) {
            e.printStackTrace();
            isSuccess=false;
        }

        if(inputStream!=null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
                isSuccess=false;
            }
        }

        if(outputStream!=null) {
            try {
                outputStream.close();
            } catch (IOException e) {
                isSuccess=false;
                e.printStackTrace();
            }
        }

        return isSuccess;
    }



}