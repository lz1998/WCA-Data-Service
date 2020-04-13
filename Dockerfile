FROM openjdk:8-alpine

WORKDIR /app
ARG JAR_FILE

COPY ${JAR_FILE} /app/app.jar

EXPOSE 5050

CMD ["java", "-jar", "/app/app.jar"]
