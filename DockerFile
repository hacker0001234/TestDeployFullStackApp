# Use an official OpenJDK runtime as a parent image
FROM eclipse-temurin:23-jdk-jammy

# Set working directory inside the container
WORKDIR /app

# Copy the jar file from target
COPY target/TestDeploy-0.0.1-SNAPSHOT.jar app.jar


EXPOSE 9090

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
