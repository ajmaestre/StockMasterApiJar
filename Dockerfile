FROM openjdk:17-jre-slim

WORKDIR /app

COPY ./StockMasterApi.jar .

CMD ["java", "-jar", "StockMasterApi.jar"]
