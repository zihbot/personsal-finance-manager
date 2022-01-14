#FROM openjdk:17-jdk-alpine
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]

FROM gradle:7.3.3-jdk17-alpine AS builder
LABEL stage=builder
WORKDIR /app
COPY build.gradle settings.gradle /app/
RUN [ "gradle", "init" ]

COPY src /app/src
RUN [ "gradle", "build" ]

FROM openjdk:17-alpine
WORKDIR /app
COPY --from=builder /app/build/libs/PersonalFinanceManager.jar ./
#ENTRYPOINT ["/bin/ash"]
CMD [ "java", "-jar", "PersonalFinanceManager.jar" ]