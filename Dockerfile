FROM openjdk:17-jdk-alpine

WORKDIR /app

# Copia los archivos JAR de las bibliotecas Jackson al contenedor
COPY lib/* /app/lib/

# Copia todos los archivos Java de tu proyecto al contenedor
COPY src /app/src

# Compila tu proyecto con las bibliotecas Jackson incluidas
RUN javac -cp "lib/*" src/*.java
