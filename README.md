# PROJECT DESCRIPTION
### a simple web application is created by aws lambda and deployed by aws apigateway, the applicaton have a function that detects user's IP and display both remote server IP and user's ip service.

---
# Prerequisites:

- maven
- jar: aws-lambda-java-core
- jar: com.googlecode.json-simple
- maven package plugin: maven-shade-plugin

    
# Deploy:
- cmd: maven clean package
- upload: aws lambda or s3

# unit test and coverge report
- cmd: mvn test surefire-report:report
- path: target/site/surefire-report.html
- path: target/site/jacoco/index.html

---
# Design specification:
## ParserIpHandler class
> ParserIpHandler implements the handleRequest method of RequestStreamHandler Lambda integration was designed by aws apigateway, which can pass the original request metadata, such as (headers) and the information of apigateway, to lambda.
> So lambda can get the requested stream through the inputstream and process it. The headers in the inputstream are forwarded. Since they have passed through the gateway, we need to forwarded through layers of proxies such as the x-forwarded-for header to get the original request IP address
> about the remote ip, use InetAddress to parse the apigateway host.

# Architecture
### cloud architecture
![Image text](https://raw.githubusercontent.com/lljuser/aws-lambda-spa/master/architecture.png)

### apigateway and lambda authoriztion
![Image text](https://raw.githubusercontent.com/lljuser/aws-lambda-spa/master/s1.jpg)

### user request workflow
![Image text](https://raw.githubusercontent.com/lljuser/aws-lambda-spa/master/s2.jpg)

