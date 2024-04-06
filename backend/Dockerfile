FROM openjdk:21-jdk-slim AS builder

WORKDIR /app

COPY gradlew .
RUN chmod +x gradlew
COPY gradle gradle
COPY settings.gradle .
COPY build.gradle .

RUN ./gradlew --version
RUN ./gradlew clean build

COPY . .
FROM openjdk:21-jdk-slim

WORKDIR /app

COPY --from=builder /app/build/libs/backend-0.0.1-SNAPSHOT.jar /app/backend.jar

EXPOSE 8080

CMD ["java", "-jar", "backend.jar"]
