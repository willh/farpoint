package com.willhamill.farpoint.consumer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FarpointConsumerConfig {

    @Value("${queueName}")
    private String queueName;

    @Value("${exchangeName}")
    private String exchangeName;

    @Value("${routingKey}")
    private String routingKey;

    public String getQueueName() {
        return queueName;
    }

    public String getExchangeName() {
        return exchangeName;
    }

    public String getRoutingKey() {
        return routingKey;
    }
}
