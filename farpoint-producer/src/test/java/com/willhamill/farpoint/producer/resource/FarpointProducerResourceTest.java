package com.willhamill.farpoint.producer.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.willhamill.farpoint.producer.MessageGeneratorService;
import com.willhamill.farpoint.producer.QueueClient;
import com.willhamill.farpoint.producer.model.FarpointMessage;
import com.willhamill.farpoint.producer.resources.FarpointProducerResource;

import io.dropwizard.testing.junit.ResourceTestRule;

import org.junit.After;
import org.junit.ClassRule;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MultivaluedHashMap;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

public class FarpointProducerResourceTest {

    private static final QueueClient mockClient = mock(QueueClient.class);
    private static final MessageGeneratorService mockService = mock(MessageGeneratorService.class);

    private FarpointMessage testMessage = new FarpointMessage("testSubject",
            "testContent", LocalDateTime.of(2000, 1, 1, 12, 34, 56));


    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new FarpointProducerResource(mockClient, mockService))
            .build();

    @After
    public void tearDown(){
        reset(mockClient);
        reset(mockService);
    }

    @Test
    public void testSendMessageCallsMessageGeneratorServiceWithData() {
        resources.client().target("/send").request().post(Entity.form(generateFormDataRequest()),
                FarpointMessage.class);

        verify(mockService).generate("testSubject", "testContent");
    }

    @Test
    public void testSendMessagePutsMessageOnQueue() throws IOException {
        when(mockService.generate("testSubject", "testContent")).thenReturn(testMessage);
        resources.client().target("/send").request().post(Entity.form(generateFormDataRequest()),
                FarpointMessage.class);

        byte[] expectedPayload = new ObjectMapper().writeValueAsString(testMessage).getBytes();

        verify(mockClient).setupQueue();
        verify(mockClient).sendMessage(expectedPayload);
        verify(mockClient).closeConnection();
    }

    @Test
    public void testSendMessageReturnsCorrectResponse() throws IOException {
        when(mockService.generate("testSubject", "testContent")).thenReturn(testMessage);
        // don't need to mock void methods in queueClient to doNothing, as that's the default

        FarpointMessage responseMessage = resources.client().target("/send").request().post(Entity.form(generateFormDataRequest()),
                FarpointMessage.class);
        assertThat(responseMessage).isEqualTo(testMessage);
    }

    private MultivaluedHashMap<String, String> generateFormDataRequest() {
        MultivaluedHashMap<String, String> formData = new MultivaluedHashMap<>();
        formData.putSingle("subject", "testSubject");
        formData.putSingle("message", "testContent");
        return formData;
    }
}
