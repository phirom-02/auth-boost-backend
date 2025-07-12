FROM openjdk:22-jdk-oracle as build

WORKDIR /app

COPY target/auth-boost-backend.jar /app/auth-boost-backend.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/auth-boost-backend.jar"]