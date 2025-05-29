# Use an official OpenJDK 24 runtime as a parent image
FROM eclipse-temurin:24-jre-alpine

# Set the working directory
WORKDIR /app

# Copy the built jar file into the container
COPY target/*.jar app.jar

# Expose the port the app runs on
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]