# compilación con Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# copia el archivo de configuración de Maven y descarga dependencias
COPY pom.xml .
RUN mvn dependency:go-offline

# copia el código fuente y genera el archivo .jar
COPY src ./src
RUN mvn clean package -DskipTests

#ejecución con Java
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copia el .jar generado desde la fase anterior
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]