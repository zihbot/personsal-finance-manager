#FROM openjdk:17-jdk-alpine
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]

FROM gradle:7.3.3-jdk17-alpine
WORKDIR /app
COPY build.gradle /app/
RUN [ "gradle", "build" ]

COPY * /app/
RUN [ "gradle", "build" ]
RUN [ "gradle", "bootJar" ]
ENTRYPOINT [ "/bin/ash" ]