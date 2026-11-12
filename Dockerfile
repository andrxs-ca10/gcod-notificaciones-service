FROM maven:3.10.0-eclipse-temurin-25-alpine AS compilacion

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn -DskipTests package

FROM eclipse-temurin:25-jre

WORKDIR /app

COPY --from=compilacion /app/target/gcod-notificaciones-service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "app.jar"]