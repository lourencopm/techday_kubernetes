FROM eclipse-temurin:17-jdk-alpine

VOLUME /tmp

COPY target/kubernetes-0.0.1-SNAPSHOT.jar app.jar

RUN mkdir /home/app

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar","--spring.config.location=file:/home/app/application.properties"]