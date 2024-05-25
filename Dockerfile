# Utiliza una imagen base con el JDK 17
FROM openjdk:17-jdk-alpine

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia todos los archivos de tu proyecto al contenedor
COPY . /app

# Compila tus archivos Java (sustituye 'Main' por el nombre de tu clase principal)
RUN javac src/main/java/com/engineerds/stockmaster/Main.java

# Comando para ejecutar tu aplicación
CMD ["java", "main.java.com.engineerds.stockmaster.Main"]
