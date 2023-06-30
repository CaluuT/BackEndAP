FROM amazoncorretto:8-alpine-jdk
COPY target/CaluT-0.0.1-SNAPSHOT.jar CaluT.jar
ENTRYPOINT ["java","-jar","/CaluT.jar"]
