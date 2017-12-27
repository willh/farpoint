package com.willhamill.farpoint.producer.resources;

import com.codahale.metrics.annotation.Timed;
import com.willhamill.farpoint.producer.QueueClient;

import java.io.IOException;

import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/")

public class FarpointProducerResource {

    private QueueClient queueClient;

    public FarpointProducerResource(QueueClient queueClient){
        this.queueClient = queueClient;
    }

    @Timed
    @POST
    @Path("send")
    public String sendMessage(@Valid String message) throws IOException {
        queueClient.setupQueue();
        queueClient.sendMessage(message);
        queueClient.closeConnection();
        return "message sent: " + message;
    }
}
