# Stage 1: Build the application
FROM eclipse-temurin:17-jdk AS builder

WORKDIR /app
COPY . .
RUN chmod +x mvnw

# Usa el perfil 'production' para que Vaadin genere el bundle en build-time
RUN ./mvnw clean package -DskipTests -Pproduction

# Stage 2: Run the application
FROM eclipse-temurin:17-jre

WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
