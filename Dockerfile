# Use an OpenJDK 17 runtime as base image
FROM adoptopenjdk/openjdk17:jdk-17.0.2_8-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the executable JAR file into the container
COPY build/libs/go2uz-1.jar app.jar

# Expose the port that the application will run on
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "app.jar"]
