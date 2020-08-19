FROM openjdk:8

LABEL Sadanand burud <burudsadanand@gmail.com>

ADD target/meetingapp-docker.jar meetingapp-docker.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","meetingapp-docker.jar"]

