package com.willhamill.farpoint.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.willhamill.farpoint.producer.config.QueueConfiguration;

import java.io.IOException;

public class QueueClient {

    private QueueConfiguration config;
    private ConnectionFactory factory;
    private Connection connection;
    private Channel channel;

    public QueueClient(QueueConfiguration queueConfiguration) {
        this.config = queueConfiguration;
    }

    public void setupQueue() throws IOException {
        openConnection();
        createExchange();
        createQueue();
    }

    public void sendMessage(String message) throws IOException {
        channel.basicPublish(config.getExchangeName(), config.getRoutingKey(), null, message.getBytes());
    }

    public void closeConnection() throws IOException {
        channel.close();
        connection.close();
    }

    private void openConnection() throws IOException {
        factory = new ConnectionFactory();
        factory.setHost(config.getQueueHost());
        factory.setPort(config.getPort());
        factory.setUsername(config.getUser());
        factory.setPassword(config.getPassword());
        factory.setConnectionTimeout(config.getTimeoutMillis());
        connection = factory.newConnection();

        channel = connection.createChannel();
    }

    private void createExchange() throws IOException {
        channel.exchangeDeclare(config.getExchangeName(), "direct", true);
    }

    private void createQueue() throws IOException {
        channel.queueDeclare(config.getQueueName(), false, false, false, null);
        channel.queueBind(config.getQueueName(), config.getExchangeName(), config.getRoutingKey());
    }
}
