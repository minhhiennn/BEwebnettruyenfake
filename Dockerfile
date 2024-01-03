FROM eclipse-temurin:17-jdk-alpine
ARG JAR_FILE=target/*.jar
RUN mvn clean install -DskipTests
COPY ./target/backend-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]