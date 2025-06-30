# Use a stable official OpenJDK runtime
FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

COPY target/TestDeploy-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 9090

ENTRYPOINT ["java", "-jar", "app.jar"]
