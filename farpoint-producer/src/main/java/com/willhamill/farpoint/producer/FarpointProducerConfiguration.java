package com.willhamill.farpoint.producer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.willhamill.farpoint.producer.config.QueueConfiguration;

import io.dropwizard.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class FarpointProducerConfiguration extends Configuration {

    @Valid
    @NotNull
    @JsonProperty
    private QueueConfiguration queueConfiguration;

    public QueueConfiguration getQueueConfiguration() {
        return queueConfiguration;
    }
}
