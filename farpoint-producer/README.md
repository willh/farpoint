# FarpointProducer

How to start the FarpointProducer application
---

1. Run `mvn clean install` to build your application
2. Start application with `java -jar target/farpoint-producer-0.1.0-SNAPSHOT.jar server config.yml`
3. To check that your application is running hit the healtcheck url `http://localhost:8081/healthcheck`


Using Docker
---

Change the config hostname for the queue to `docker.for.mac.localhost` to ensure the container uses the host loopback rather than looking inside the container for RabbitMQ

Build the container with `docker build -t farpoint-producer-test .`

Run the container locally, binding the app port 8080 and healthcheck port 8081 with `docker run -p 8080:8080 -p 8081:8081 -it farpoint-producer-test`


Health Check
---

The FarpointProducer app has a healhcheck on this url `http://localhost:8081/healthcheck`

The healthcheck will respond positively if the service is up and it can make a connection to RabbitMQ using the supplied config
