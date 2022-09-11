FROM openjdk:18

RUN mkdir "/app"

ADD target/spring-security-batch-jpa.jar /app/spring-security-batch-jpa.jar
ADD /script.sh /app/script.sh
#RUN apt-get update
#RUN apt-get install -y netcat

#EXPOSE 8086

#ENTRYPOINT ["/app/script.sh"]
#CMD ["nginx"]
#ENTRYPOINT ["/script.sh"]
CMD java -jar /app/spring-security-batch-jpa.jar