FROM openjdk:8-jdk-alpine

VOLUME /tmp

RUN apk add --no-cache python

EXPOSE 8010

ADD target/gs-actuator-service-0.1.0.jar app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

