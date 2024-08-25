FROM maven:3.9.8-eclipse-temurin-21-alpine AS build

WORKDIR /app

COPY pom.xml ./

COPY src ./src

RUN mvn clean package

FROM eclipse-temurin:21-alpine

WORKDIR /app

COPY --from=build /app/target/accountstorage.jar /app/accountstorage.jar

ENTRYPOINT ["java", "-jar", "accountstorage.jar"]