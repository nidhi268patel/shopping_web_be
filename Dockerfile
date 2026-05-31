FROM eclipse-temurin:20-jdk AS build

WORKDIR /app
COPY . .

RUN chmod +x ./gradlew
RUN ./gradlew clean build -x test

FROM eclipse-temurin:20-jre

WORKDIR /app

COPY --from=build /app/build/libs/*SNAPSHOT*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]