FROM openjdk:17
COPY /build/libs/Catus-1.0.jar catus.jar
ENTRYPOINT ["java", "-jar", "/catus.jar"]