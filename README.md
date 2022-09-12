# Read Me First

This project authenticates the user and according to the role it authorizes the user to access to run the .csv batch file to store the data into MySQL database. 

Team members:

    Mohammad Meraj Khan - 614631
    Safiquel Islam - 

# Getting Started

### How to Test the Application
#### We have uploaded the application image to docker hub, please use the docker-compose.yml file to deploy the images to local docker for the testing purposes.Please go to the folder path of docker-compose.yml and execute the following two commands. 
    docker compose build
    docker compose up

### Model
    Three models: User, Role, Person

### API endpoint

#### Signup 

    For the client app please use any rest client(like Postman).Please add a 
    httpHeader "Content-Type" and set "application/json" as the value.

    Endpoint:  api/auth/signup
    Body: {
        "username":"",
        "email": "",
        "password": "",
        "role": ["admin"]
        }
    HTTP method: "POST"

#### Signin

    Endpoint: api/auth/signin
    Body: {
        "username": "",
        "password": ""
        }
    Method: "POST"

This generates a JWT token for  authorization and then use the token as header in authorization field to start the batch

#### Run Batch
    
    Endpoint: api/test/admin
    Header: "Authorization": "BEARER ....JWT Token...."
    HTTP Method: "GET"    

With admin permission it will run the batch. It uses spring batch item reader, processing and writer to process the data

### Docker 

Before uploading in docker, we have to configure three files "Dockerfile", "docker-compose.yml" and "application-properties" 

### Dockerfile
    FROM openjdk:18

    RUN mkdir "/app"

    ADD target/spring-security-batch-jpa.jar /app/spring-security-batch-jpa.jar

    ADD /script.sh /app/script.sh

    CMD java -jar /app/spring-security-batch-jpa.jar

### docker-compose.yml

    version: "3.9"
    services:
        mysqldb:
            image: mysql:latest
        ports:
          - 3307:3306
        healthcheck:
          test: ["CMD", "mysqladmin", "ping", "-h", "localhost"]
          timeout: 30s
          retries: 10
        restart: on-failure
        #networks:
          #- sb-net
        environment:
          - MYSQL_DATABASE=swaproj
          - MYSQL_ROOT_PASSWORD=123456
    app:
        image: merajkhan/swa:firsttry
        #build: .
        ports:
          - 8086:8086
        environment:
          - MYSQL_URL=mysqldb
        #networks:
         #- sb-net
        depends_on:
          mysqldb:
            condition: service_healthy

    #networks:
     #  sb-net:
     #    name: sb-net
     #    driver: bridge

Then run command docker compose up. Two separate created, one for application and another is for mysql Database. 

### Github Repository 

    https://github.com/MdMerajKhan/swaprojone/tree/master

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.0/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.0/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.7.0/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.7.0/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Spring Security](https://docs.spring.io/spring-boot/docs/2.7.0/reference/htmlsingle/#boot-features-security)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)

