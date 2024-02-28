FROM openjdk:17-jdk-slim AS build

COPY target/image-1-0.0.1-SNAPSHOT.jar image-1.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/image-1.jar"]