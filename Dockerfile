#FROM maven:3-openjdk-17-slim AS build
#COPY src /home/app/src
#COPY pom.xml /home/app
#RUN mvn -DskipTests -f /home/app/pom.xml clean package

FROM openjdk:17-jdk-alpine AS run
EXPOSE ${SPRINGBOOT_PORT}
#COPY --from=build /home/app/target/*.jar /usr/local/lib/service.jar
COPY ./target/*.jar /usr/local/lib/service.jar

WORKDIR /usr/local/lib
ENTRYPOINT ["java","-jar","service.jar"]