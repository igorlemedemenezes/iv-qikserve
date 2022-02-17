FROM openjdk

WORKDIR /app

COPY target/qikserve-0.0.1-SNAPSHOT.jar /app/qikserve.jar

ENTRYPOINT ["java", "-jar", "qikserve.jar"]