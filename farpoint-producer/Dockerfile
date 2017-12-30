FROM openjdk:8u151-jdk-alpine
MAINTAINER Will Hamill http://willhamill.com

COPY target/farpoint-producer-0.1.0-SNAPSHOT.jar /opt/farpoint-producer/farpoint-producer-0.1.0-SNAPSHOT.jar
COPY config.yml /etc/farpoint-producer/config.yml

WORKDIR /opt/farpoint-producer

CMD ["java", "-jar", "farpoint-producer-0.1.0-SNAPSHOT.jar", "server", "/etc/farpoint-producer/config.yml"]

EXPOSE 8080-8081
