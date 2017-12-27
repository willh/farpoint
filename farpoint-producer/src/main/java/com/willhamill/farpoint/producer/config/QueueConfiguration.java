package com.willhamill.farpoint.producer.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class QueueConfiguration {

    @Valid
    @NotNull
    @JsonProperty
    private String queueName;

    public String getQueueName() {
        return queueName;
    }

    @Valid
    @NotNull
    @JsonProperty
    private String exchangeName;

    public String getExchangeName() {
        return exchangeName;
    }

    @Valid
    @NotNull
    @JsonProperty
    private String queueHost;

    public String getQueueHost() {
        return queueHost;
    }

    @Valid
    @NotNull
    @JsonProperty
    private String routingKey;

    public String getRoutingKey() {
        return routingKey;
    }

    @Valid
    @NotNull
    @JsonProperty
    private int port;

    public int getPort() {
        return port;
    }

    @Valid
    @NotNull
    @JsonProperty
    private String user;

    public String getUser() {
        return user;
    }

    @Valid
    @NotNull
    @JsonProperty
    private String password;

    public String getPassword() {
        return password;
    }
}
