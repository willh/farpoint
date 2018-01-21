package com.willhamill.farpoint.producer;

import com.willhamill.farpoint.producer.model.FarpointMessage;

import java.time.LocalDateTime;

public class MessageGeneratorService {

    public FarpointMessage generate(String subject, String content) {
        return new FarpointMessage(subject, content, LocalDateTime.now());
    }
}
