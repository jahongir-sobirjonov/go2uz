##FROM ubuntu:latest
##LABEL authors="alfatech.uz"
##
##ENTRYPOINT ["top", "-b"]
#FROM ubuntu:latest AS build
#
#RUN apt-get update
#RUN apt-get install openjdk-19-jdk -y
#COPY . .
#
#RUN ./gradlew bootJar --no-daemon
#
#FROM openjdk:19-jdk-slim
#
#EXPOSE 8080
#
#COPY --from=build /build/libs/go2uz-1.jar app.jar
#
#ENTRYPOINT ["java", "-jar", "app.jar"]
# Use Gradle to build the project
#FROM gradle:7.3.3-jdk17 AS build
#COPY . .
#RUN gradle clean build -x test
#
## Use a lightweight image to run the application
#FROM openjdk:22-jdk-slim
#COPY --from=build /build/libs/go2uz-1.jar app.jar
#EXPOSE 8080
#CMD ["java", "-jar", "app.jar"]


# Use OpenJDK 19 base image
# Use OpenJDK 19 base image
#FROM openjdk:19-jdk
#
## Set working directory inside the container
#WORKDIR /app
#
## Copy Gradle build files
#COPY build.gradle settings.gradle gradlew ./
#COPY gradle/ ./gradle/
#
## Copy the entire source tree
#COPY src ./src
#
## Grant execute permissions to Gradle wrapper
#RUN chmod +x ./gradlew
#
## Build application using Gradle
#RUN ./gradlew build -x test || true   # Use '|| true' to ignore errors temporarily
#
## Expose port 8080
#EXPOSE 8080
#
## Run the Spring Boot application when the container starts
#ENTRYPOINT ["java", "-jar", "build/libs/go2uz.jar"]
#
#
#FROM ubuntu:latest AS build
#
#RUN apt-get update
#RUN apt-get install openjdk-17-jdk -y
#COPY . .
#
#RUN ./gradlew bootJar --no-daemon
#
#FROM openjdk:17-jdk-slim
#
#EXPOSE 8080
#
#COPY --from=build /build/libs/go2uz-1.jar-1.jar go2uz.jar
#
#ENTRYPOINT ["java", "-jar", "go2uz.jar"]
#
# Build stage
#




# Use an official OpenJDK runtime as a parent image
FROM openjdk:19-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the executable JAR file into the container
COPY build/libs/go2uz-1.jar app.jar

# Expose the port that the application will run on
EXPOSE 8080

# Define the command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]















#FROM gradle:7.4.2-jdk17 AS build
#WORKDIR /app
#COPY --chown=gradle:gradle . /app/
#RUN gradle clean build
#
##
## Package stage
##
#FROM openjdk:17-alpine
#WORKDIR /app
#COPY --from=build /app/build/libs/*.jar /app/app.jar
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","app.jar"]
