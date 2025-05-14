FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

COPY backend /app
WORKDIR /app

RUN ./mvnw clean install -DskipTests

CMD ["java", "-jar", "target/back-0.0.1-SNAPSHOT.jar"]
