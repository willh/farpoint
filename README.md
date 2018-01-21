# Farpoint

#### so named because it's a queue system, geddit?

These applications are designed to form a working queue producer/consumer setup in just enough depth to make learning how to rebuild, upgrade, containerise, deploy and replace [etc] them realistic.

`farpoint-producer` puts messages on a queue when form data is posted to its http endpoint. This app is a Dropwizard web service.

`farpoint-consumer` reads messages off the queue and does some work on them. This app is a Spring Boot queue listener.

See the readme in each individual application for instructions on how to build and run.

RabbitMQ is initially provided as the queue broker, and setup instructions are provided in `rabbitmq_setup.sh` to get a local install with non-default user up and running.