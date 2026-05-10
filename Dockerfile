# Stage 1: Build the application
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app
COPY . .
# Windows format ko handle karne aur gradlew ko executable banane ke liye
RUN chmod +x ./gradlew
RUN ./gradlew clean build -x test

# Stage 2: Run the application
FROM eclipse-temurin:20-jre
WORKDIR /app
# Sirf main executable jar ko copy karein (plain jar ko ignore karein)
COPY --from=build /app/build/libs/*SNAPSHOT*.jar app.jar
RUN find ./libs -name "*.jar" ! -name "*-plain.jar" -exec cp {} app.jar \;
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]