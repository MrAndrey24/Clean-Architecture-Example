FROM amazoncorretto:21-alpine
ARG JAR_FILE=build/libs/clean-architecterue-crud-1.1.1.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]