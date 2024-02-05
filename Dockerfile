FROM openjdk:17

ARG JAR_FILE=target/*.jar

COPY ./target/scheduling-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "/app.jar"]
