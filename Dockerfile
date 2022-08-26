FROM openjdk:8-jdk-alpine

ADD target/SpringBoot-0.0.1-SNAPSHOT.jar myapp.jar

EXPOSE 8889

ENTRYPOINT ["java","-jar","/myapp.jar"]