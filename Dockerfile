# Utiliza una imagen base con JRE (Java Runtime Environment) 17
FROM openjdk:17-jdk-alpine

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR de tu aplicación al contenedor
COPY StockMasterApi.jar /app/

# Comando para ejecutar tu aplicación cuando el contenedor se inicie
CMD ["java", "-jar", "StockMasterApi.jar"]
