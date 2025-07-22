FROM openjdk:22-jdk-oracle as build

WORKDIR /app

COPY target/*.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java" ,"-jar", "/app/app.jar", "--spring.profiles.active=dev"]