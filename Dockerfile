# Usa una imagen con JDK 21 (compatible con Spring Boot 3.5.0)
FROM eclipse-temurin:21-jdk-jammy as builder

# Directorio de trabajo
WORKDIR /app

# Configura variables de entorno para Gradle
ENV GRADLE_OPTS="-Dorg.gradle.daemon=false"

# Copia solo los archivos necesarios para instalar dependencias primero
COPY gradlew .
COPY gradle gradle
COPY build.gradle.kts .
COPY settings.gradle.kts .
COPY src src

# Da permisos de ejecución a gradlew
RUN chmod +x gradlew

# Descarga dependencias (para mejor caching)
RUN ./gradlew --no-daemon dependencies

# Construye la aplicación
RUN ./gradlew --no-daemon bootJar

# Segunda etapa para la imagen final (más ligera)
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

# Copia el JAR construido desde la primera etapa
COPY --from=builder /app/build/libs/*.jar app.jar

# Puerto que expone la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]