FROM openjdk:19
EXPOSE 8080

COPY --from=build /build/libs/go2uz-0.0.1-SNAPSHOT.jar go2uz-app.jar
ENTRYPOINT ["java", "-jar", "go2uz-app"]
