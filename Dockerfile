FROM openjdk:19
EXPOSE 8080
ARG JAR_FILE=build/libs/go2uz-1.jar-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} go2uz
ENTRYPOINT ["java", "-jar", "go2uz"]

