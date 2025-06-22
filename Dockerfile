# Usa una imagen con JDK 17
FROM eclipse-temurin:17-jdk-jammy as builder

# Directorio de trabajo
WORKDIR /app

# Copia solo los archivos necesarios para instalar dependencias primero
COPY gradlew .
COPY gradle gradle
COPY build.gradle.kts .
COPY settings.gradle.kts .
COPY src src

# Da permisos de ejecución a gradlew (esto soluciona el error)
RUN chmod +x gradlew

# Instala dependencias primero (para mejor caching)
RUN ./gradlew dependencies

# Construye la aplicación
RUN ./gradlew bootJar

# Segunda etapa para la imagen final (más ligera)
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Copia el JAR construido desde la primera etapa
COPY --from=builder /app/build/libs/*.jar app.jar

# Puerto que expone la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]