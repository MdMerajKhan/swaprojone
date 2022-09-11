# swaprojone
Simple project illustrating the basic usages of spring boot, spring security and docker

# Read Me First

This project authenticates the user and according to the role it authorizes the user to access to run the .csv batch file to store the data into MySQL database. 

# Getting Started

### Model
    Three models: User, Role, Person

### API endpoint

#### Signup 

    Endpoint:  api/auth/signup
    Body: {
        "username":"",
        "email": "",
        "password": "",
        "roles": ["admin","moderator"]
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
    HTTP Method: "POST"    

With admin permission it will run the batch. It uses spring batch item reader, processing and writer to process the data

### Docker 

Before uploading in docker, we have to configure three files "Dockerfile", "docker-compose.yml" and "application-properties" 

### Dockerfile
    FROM openjdk:11

    RUN mkdir "/app"

    ADD target/spring-security-batch-jpa-0.0.1-SNAPSHOT.jar /app/spring-security-batch-jpa-0.0.1-SNAPSHOT.jar

    EXPOSE 8086

    CMD java -jar /app/spring-security-batch-jpa-0.0.1-SNAPSHOT.jar

### docker-compose.yml

    version: "3"
    services:
        mysql-standalone:
            image: mysql:5.6
            ports:
              - 3305:3306
            environment:
              - MYSQL_DATABASE=swaproj
              - MYSQL_ROOT_PASSWORD=""
        app:
            build: .
            ports:
              - 8086:8086
            environment:
              SPRING_DATASOURCE_URL: jdbc:mysql://mysql-standalone:3306/swaproj?autoReconnect=true&useSSL=false
            depends_on:
              - mysql-standalone

Then run command docker compose up. Two separate created, one for application and another is for mysql Database. 

### Github Repository 

    https://github.com/MdMerajKhan/swaprojone

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
