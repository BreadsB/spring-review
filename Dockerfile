FROM eclipse-temurin:17-jdk-jammy
WORKDIR /notes
COPY build/libs/spring-review-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]