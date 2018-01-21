package com.willhamill.farpoint.consumer.serialisation;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class LocalDateTimeDeserialiser extends JsonDeserializer<LocalDateTime> {
    public LocalDateTimeDeserialiser() {
    }

    // create local date time from string containing long milliseconds since epoch
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.parseLong(jsonParser.getText())), ZoneOffset.UTC);
    }

}
