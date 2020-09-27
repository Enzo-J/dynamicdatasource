FROM openjdk:8-jdk-alpine
MAINTAINER szwg
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
VOLUME /logs
ARG JAR_FILE=target/${project.projectFullName}.jar
COPY <#noparse>${</#noparse>JAR_FILE<#noparse>}</#noparse> app.jar
ENTRYPOINT ["java","-jar","/app.jar"]