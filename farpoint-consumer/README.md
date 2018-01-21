# FarpointConsumer

How to start the FarpointConsumer application
---

1. Run `mvn clean install` to build your application
2. Start application with `java -jar target/farpoint-consumer-0.1.0-SNAPSHOT.jar`


Using Docker
---

Change the config hostname for the queue to `docker.for.mac.localhost` to ensure the container uses the host loopback rather than looking inside the container for RabbitMQ

Build the container with `docker build -t farpoint-consumer-test .`

Run the container with `docker run -it farpoint-consumer-test`


