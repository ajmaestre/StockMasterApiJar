# Usa una imagen base con el JDK 17
FROM openjdk:17-jdk-alpine

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo de compilación de tu proyecto al contenedor
COPY . /app

# Compila tu proyecto (por ejemplo, usando Maven)
RUN ./mvnw package

# Comando para ejecutar tu aplicación
CMD ["java", "-jar", "target/tu-aplicacion.jar"]
