package com.willhamill.farpoint.producer;

import com.willhamill.farpoint.producer.model.FarpointMessage;

import java.util.Date;

public class MessageGeneratorService {

    public FarpointMessage generate(String subject, String content) {
        return new FarpointMessage(subject, content, new Date());
    }
}
