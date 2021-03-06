package com.willhamill.farpoint.consumer;

import com.rabbitmq.client.Channel;
import com.willhamill.farpoint.consumer.model.FarpointMessage;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MessageReceiver {

    @Value("${workFactorMillis}")
    private int workFactorMillis;

    @RabbitListener(queues = "${queueName}")
    public void receiveMessage(FarpointMessage fpMessage, Message originalMessage, Channel channel) throws InterruptedException, IOException {
        System.out.println("Received message");

        // simulate doing some actual work (anything taking longer than 5s will run beyond soft shutdown grace period)
        Thread.sleep(workFactorMillis);

        System.out.println("Processed message: <"+ fpMessage.toString()+ ">");

        // acknowledge the message [in this sample I prefer manual acks so if process dies, message stays on queue]
        channel.basicAck(originalMessage.getMessageProperties().getDeliveryTag(), false);
    }

}
