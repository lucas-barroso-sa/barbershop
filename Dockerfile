# --- ESTÁGIO 1: Build da Aplicação ---
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
# Copia os arquivos de dependência primeiro para aproveitar o cache do Docker
COPY pom.xml .
RUN mvn dependency:go-offline
# Copia o código-fonte e gera o pacote
COPY src ./src
RUN mvn clean package -DskipTests

# --- ESTÁGIO 2: Execução Otimizada ---
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
# Pega apenas o arquivo .jar gerado no estágio anterior
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080

# Trava de segurança: Limita a memória inicial a 256MB e a máxima a 512MB
ENTRYPOINT ["java", "-Xms256m", "-Xmx512m", "-jar", "app.jar"]