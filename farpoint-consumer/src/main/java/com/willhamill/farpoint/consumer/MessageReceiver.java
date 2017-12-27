package com.willhamill.farpoint.consumer;

import com.rabbitmq.client.Channel;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MessageReceiver {

    @RabbitListener(queues = "${queueName}")
    public void receiveMessage(byte[] message, Message originalMessage, Channel channel) throws InterruptedException, IOException {
        System.out.println("Parsing message");
        String msg = new String(message);

        // simulate doing some actual work
        Thread.sleep(2000);

        System.out.println("Processed message: <"+ msg + ">");

        // acknowledge the message [in this sample I prefer manual acks so if process dies, message stays on queue]
        channel.basicAck(originalMessage.getMessageProperties().getDeliveryTag(), false);
    }

}
