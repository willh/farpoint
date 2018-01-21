package com.willhamill.farpoint.producer.resources;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.willhamill.farpoint.producer.MessageGeneratorService;
import com.willhamill.farpoint.producer.QueueClient;
import com.willhamill.farpoint.producer.model.FarpointMessage;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")

public class FarpointProducerResource {

    private QueueClient queueClient;
    private MessageGeneratorService msgService;

    public FarpointProducerResource(QueueClient queueClient, MessageGeneratorService msgService){
        this.queueClient = queueClient;
        this.msgService = msgService;
    }

    @Timed
    @POST
    @Path("send")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public FarpointMessage sendMessage(@FormParam("subject") String subject,
                                       @FormParam("message") String message) throws IOException {
        FarpointMessage fpMsg = msgService.generate(subject, message);
        byte[] payload = new ObjectMapper().writeValueAsString(fpMsg).getBytes();

        queueClient.setupQueue();
        queueClient.sendMessage(payload);
        queueClient.closeConnection();

        return fpMsg;
    }
}
