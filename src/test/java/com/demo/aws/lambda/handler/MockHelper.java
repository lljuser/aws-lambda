/**
 * Alipay.com Inc. Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.demo.aws.lambda.handler;

import java.io.*;

/**
 * MockHelper create the inpustream and outputstream mock the lambda function params
 * @author ljliu
 * @version $Id: MockHelper.java, v 0.1 2019年03月01日 下午4:23 ljliu Exp $
 */
public class MockHelper {

    /**
     * mock the inputstream like the apigateway send to the lambda function
     * @return
     */
    public static InputStream readMockRequest(String fileName){
        String filePath = MockHelper.class.getClassLoader().getResource(fileName).getPath();

        FileInputStream fileInputStream=null;
        File file=new File(filePath);
        if(file.exists()){
            try {
                fileInputStream = new FileInputStream(file);
            } catch (FileNotFoundException e) {

            }
        }

        return fileInputStream;

    }

    /**
     * mock outputstream like the lambda funciton return the response to apigateway and user
     * @return
     */
    public static OutputStream saveMockReponse(String fileName){
        String filePath = MockHelper.class.getClassLoader().getResource(fileName).getPath();
        FileOutputStream fileOutputStream=null;

        File file=new File(filePath);
        if(file.exists()){
            try {
                FileWriter fileWriter =new FileWriter(file);
                fileWriter.write("");
                fileWriter.flush();
                fileWriter.close();

            } catch (IOException e) {

            }
        }else{
            try {
                file.createNewFile();
            } catch (IOException e) {

            }
        }

        try {
            fileOutputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {

        }

        return fileOutputStream;
    }
}