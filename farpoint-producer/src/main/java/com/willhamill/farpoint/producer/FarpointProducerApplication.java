package com.willhamill.farpoint.producer;

import com.willhamill.farpoint.producer.health.FarpointProducerHealthcheck;
import com.willhamill.farpoint.producer.resources.FarpointProducerResource;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.io.IOException;

public class FarpointProducerApplication extends Application<FarpointProducerConfiguration> {

    public static void main(final String[] args) throws Exception {
        new FarpointProducerApplication().run(args);
    }

    @Override
    public String getName() {
        return "FarpointProducer";
    }

    @Override
    public void initialize(final Bootstrap<FarpointProducerConfiguration> bootstrap) {

    }

    @Override
    public void run(final FarpointProducerConfiguration configuration,
                    final Environment environment) throws IOException {
        QueueClient queueClient = new QueueClient(configuration.getQueueConfiguration());
        FarpointProducerResource farpointProducerResource = new FarpointProducerResource(queueClient);

        environment.jersey().register(farpointProducerResource);

        environment.healthChecks().register("farpoint-producer", new FarpointProducerHealthcheck());
    }

}
