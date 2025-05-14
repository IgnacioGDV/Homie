# Stage 1: Build the application
FROM eclipse-temurin:17-jdk AS builder

# Set working directory to /app/front
WORKDIR /app

# Copy only what's needed for the build
COPY front/ ./front/
WORKDIR /app/front

# Give execute permission to mvnw
RUN chmod +x mvnw

# Build the application (skip tests for speed)
RUN ./mvnw clean package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:17-jre

# Set working directory
WORKDIR /app

# Copy the JAR from builder stage
COPY --from=builder /app/front/target/*.jar app.jar

# Expose the port
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
