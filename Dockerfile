FROM amazoncorretto:17-alpine-jdk
LABEL maintainer="beyzanur";

EXPOSE 8086

COPY target/simpra-case-study-0.0.1-SNAPSHOT.jar  casestudy.jar

ENTRYPOINT ["java","-jar","/casestudy.jar"]