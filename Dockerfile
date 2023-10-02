FROM eclipse-temurin:17-jdk-jammy
WORKDIR /notes
VOLUME /tmp
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]