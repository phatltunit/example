# Use a base image with Java 17
FROM openjdk:8-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the pre-built JAR file
COPY target/example-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the Spring Boot application runs on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
