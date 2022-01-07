FROM openjdk:11-jre-slim
ARG JAR_FILE=./build/libs/PatientMSA-1.0.0.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=docker","app.jar"]