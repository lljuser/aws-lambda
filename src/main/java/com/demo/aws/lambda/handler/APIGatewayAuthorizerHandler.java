/**
 * Alipay.com Inc. Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.demo.aws.lambda.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

/**
 *
 * @author ljliu
 * @version $Id: APIGatewayAuthorizerHandler.java, v 0.1 2019年03月03日 下午6:34 ljliu Exp $
 */
public class APIGatewayAuthorizerHandler implements RequestHandler<TokenAuthorizerContext,AuthPolicy > {
    public AuthPolicy handleRequest(TokenAuthorizerContext tokenAuthorizerContext, Context context) {
        String token = tokenAuthorizerContext.getAuthorizationToken();

        String principalId = "rick.liu";


        String methodArn = tokenAuthorizerContext.getMethodArn();
        String[] arnPartials = methodArn.split(":");
        String region = arnPartials[3];
        String awsAccountId = arnPartials[4];
        String[] apiGatewayArnPartials = arnPartials[5].split("/");
        String restApiId = apiGatewayArnPartials[0];
        String stage = apiGatewayArnPartials[1];
        String httpMethod = apiGatewayArnPartials[2];
        String resource = ""; // root resource
        if (apiGatewayArnPartials.length == 4) {
            resource = apiGatewayArnPartials[3];
        }


        return new AuthPolicy(principalId, AuthPolicy.PolicyDocument.getDenyAllPolicy(region, awsAccountId, restApiId, stage));
    }
}