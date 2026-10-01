# Etape de compilation
FROM eclipse-temurin:17 as builder
WORKDIR /app
COPY pom.xml .
COPY .mvn /app/.mvn
COPY mvnw .
COPY src /app/src
RUN chmod +x mvnw
RUN ./mvnw package -DskipTests

#Etape RunTime
FROM eclipse-temurin:17-jre as runTime
#tu aurais pu copié le jar exactement dans app.jar puisque dasn le entrypoint on cherche app.jar!!
COPY --from=builder app/target/*.jar app/target/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/target/app.jar"]



