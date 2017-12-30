FROM openjdk:8u151-jdk-alpine
MAINTAINER Will Hamill http://willhamill.com

COPY target/farpoint-consumer-0.1.0-SNAPSHOT.jar /opt/farpoint-consumer/farpoint-consumer-0.1.0-SNAPSHOT.jar
COPY application.yml /etc/farpoint-consumer/application.yml

WORKDIR /opt/farpoint-consumer

CMD ["java", "-jar", "farpoint-consumer-0.1.0-SNAPSHOT.jar", "--spring.config.location=/etc/farpoint-consumer/application.yml"]
