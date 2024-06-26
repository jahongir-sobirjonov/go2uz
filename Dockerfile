FROM adoptopenjdk/openjdk18:alpine

WORKDIR /app

COPY build/libs/go2uz-1.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
