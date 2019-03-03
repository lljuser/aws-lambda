/**
 * Alipay.com Inc. Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.demo.aws.lambda.handler;

/**
 *
 * @author ljliu
 * @version $Id: TokenAuthorizerContext.java, v 0.1 2019年03月03日 下午6:46 ljliu Exp $
 */
public class TokenAuthorizerContext {
    /**
     * JSON input is deserialized into this object representation
     * @param type Static value - TOKEN
     * @param authorizationToken - Incoming bearer token sent by a client
     * @param methodArn - The API Gateway method ARN that a client requested
     */
    public TokenAuthorizerContext(String type, String authorizationToken, String methodArn) {
        this.type = type;
        this.authorizationToken = authorizationToken;
        this.methodArn = methodArn;
    }

    public TokenAuthorizerContext() {

    }

    String type;
    String authorizationToken;
    String methodArn;

    /**
     * Getter method for property <tt>type</tt>.
     *
     * @return property value of type
     */
    public String getType() {
        return type;
    }

    /**
     * Setter method for property <tt>type</tt>.
     *
     * @param type value to be assigned to property type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Getter method for property <tt>authorizationToken</tt>.
     *
     * @return property value of authorizationToken
     */
    public String getAuthorizationToken() {
        return authorizationToken;
    }

    /**
     * Setter method for property <tt>authorizationToken</tt>.
     *
     * @param authorizationToken value to be assigned to property authorizationToken
     */
    public void setAuthorizationToken(String authorizationToken) {
        this.authorizationToken = authorizationToken;
    }

    /**
     * Getter method for property <tt>methodArn</tt>.
     *
     * @return property value of methodArn
     */
    public String getMethodArn() {
        return methodArn;
    }

    /**
     * Setter method for property <tt>methodArn</tt>.
     *
     * @param methodArn value to be assigned to property methodArn
     */
    public void setMethodArn(String methodArn) {
        this.methodArn = methodArn;
    }





}