
# Spring REST API OpenBank  - RESTful Service 

RESTful API that retrieves transactions from OpenBank sandbox

# Install

You should be able to start the application using maven and the next command

** mvn clean install tomcat7:run

This will install spring-rest-api-openbank over a embebbed Tomcat 7 webserver which starts  the api on port 8080 and can be accessed (http://localhost:8080)

# Documentation

The next URL will show the SwaggerUI documentation for the API

** http://localhost:8080/spring-rest-api-openbank/

# Security

The API uses Spring security, Rest services can be consumed using the next user and password

** User: admin
** pass: admin

# Logs 

API logs can be found in the root folder with the name 

** spring-rest-api-openbank.log