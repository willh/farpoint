package com.willhamill.farpoint.producer.health;

import com.codahale.metrics.health.HealthCheck;

public class FarpointProducerHealthcheck extends HealthCheck {

    @Override
    protected Result check() {
        return Result.healthy();
    }
}
