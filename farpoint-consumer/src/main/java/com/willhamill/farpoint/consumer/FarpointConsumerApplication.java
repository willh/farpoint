package com.willhamill.farpoint.consumer;


import com.willhamill.farpoint.consumer.config.FarpointConsumerConfig;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableRabbit
public class FarpointConsumerApplication {

    @Bean
    FarpointConsumerConfig config() {
        return new FarpointConsumerConfig();
    }

    @Bean
    Queue queue() {
        return new Queue(config().getQueueName(), false);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(config().getExchangeName());
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(config().getRoutingKey());
    }

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(FarpointConsumerApplication.class, args);
    }
}
