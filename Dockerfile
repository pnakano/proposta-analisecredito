FROM openjdk:17

COPY target/proposta-analisecredito-1.0.0-SNAPSHOT.jar proposta-analisecredito.jar

ENTRYPOINT ["java", "-jar", "proposta-analisecredito.jar"]