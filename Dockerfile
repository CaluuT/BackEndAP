FROM amazoncorretto:11-alpine-jdk
MAINTAINER CaluT
COPY target/CaluT-0.0.1-SNAPSHOT.jar CaluT.jar
ENTRYPOINT ["java","-jar","/CaluT.jar"]
