package com.willhamill.farpoint.producer.serialisation;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class LocalDateTimeSerialiser extends JsonSerializer<LocalDateTime> {
    public LocalDateTimeSerialiser() {
    }

    public void serialize(LocalDateTime datetime, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeString(Long.toString(datetime.toInstant(ZoneOffset.UTC).toEpochMilli()));
    }


}
