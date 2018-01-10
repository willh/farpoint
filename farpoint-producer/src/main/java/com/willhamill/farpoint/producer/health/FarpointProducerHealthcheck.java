package com.willhamill.farpoint.producer.health;

import com.codahale.metrics.health.HealthCheck;
import com.willhamill.farpoint.producer.QueueClient;

import java.io.IOException;

public class FarpointProducerHealthcheck extends HealthCheck {

    private QueueClient queueClient;

    public FarpointProducerHealthcheck(QueueClient queueClient) {
        this.queueClient = queueClient;
    }

    @Override
    protected Result check() throws IOException
    {
        try {
            queueClient.openConnection();
            queueClient.closeConnection();
        } catch (IOException io) {
            return Result.unhealthy("Could not establish queue connection");
        }

        return Result.healthy();
    }
}
