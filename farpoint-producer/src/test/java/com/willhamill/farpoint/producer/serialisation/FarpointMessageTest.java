package com.willhamill.farpoint.producer.serialisation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.willhamill.farpoint.producer.model.FarpointMessage;

import io.dropwizard.jackson.Jackson;

import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;

import static io.dropwizard.testing.FixtureHelpers.fixture;

import static org.assertj.core.api.Assertions.assertThat;

public class FarpointMessageTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
    private static final LocalDateTime testDate = LocalDateTime.of(2000, 1, 1, 12, 34, 56);

    @Test
    public void serialisesToJSON() throws Exception {
        final FarpointMessage fpMsg = new FarpointMessage("testSubject", "testContent", testDate);
        final String expected = fixture("fixtures/farpointmessage.json");
        assertThat(MAPPER.writeValueAsString(fpMsg)).isEqualTo(expected);
    }

    @Test
    public void deserializesFromJSON() throws IOException {
        final FarpointMessage fpMsg = new FarpointMessage("testSubject", "testContent", testDate);
        assertThat(MAPPER.readValue(fixture("fixtures/farpointmessage.json"), FarpointMessage.class))
        .isEqualTo(fpMsg);
    }
}
