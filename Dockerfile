# Usa una imagen con JDK 17 (la versión que usa Spring Boot 3.5.0)
FROM eclipse-temurin:17-jdk-jammy as builder

# Directorio de trabajo
WORKDIR /app

# Copia los archivos de construcción
COPY . .

# Construye la aplicación
RUN ./gradlew bootJar

# Segunda etapa para la imagen final (más ligera)
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Copia el JAR construido desde la primera etapa
COPY --from=builder /app/build/libs/*.jar app.jar

# Puerto que expone la aplicación (debe coincidir con tu server.port)
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]