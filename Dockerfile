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
FROM gradle:7.3.3-jdk17 AS build
COPY . .
RUN gradle clean build -x test

# Use a lightweight image to run the application
FROM openjdk:17.0.1-jdk-slim
COPY --from=build /home/gradle/build/libs/demo-0.0.1-SNAPSHOT.jar demo.jar
EXPOSE 8080
CMD ["java", "-jar", "demo.jar"]
