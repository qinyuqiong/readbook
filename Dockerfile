FROM    openjdk:11-jdk-alpine
VOLUME /tmp
COPY ./target/readbook.jar readbook.jar
ENTRYPOINT ["java","-jar","/readbook.jar","&"]