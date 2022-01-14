FROM openjdk:8-alpine
ADD "./target/Prueba-0.0.1-SNAPSHOT.jar" "app.jar"
EXPOSE 80
ENTRYPOINT ["java", "-jar", "app.jar"]