package com.willhamill.farpoint.consumer.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.willhamill.farpoint.consumer.serialisation.LocalDateTimeDeserialiser;
import com.willhamill.farpoint.consumer.serialisation.LocalDateTimeSerialiser;

import java.io.Serializable;
import java.time.LocalDateTime;

public final class FarpointMessage implements Serializable {

    private String subject;
    private String content;
    private LocalDateTime sendTime;

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    @JsonSerialize(using = LocalDateTimeSerialiser.class)
    @JsonDeserialize(using = LocalDateTimeDeserialiser.class)
    public LocalDateTime getSendTime() {
        return sendTime;
    }

    public FarpointMessage() {
    }

    public FarpointMessage(String subject, String content, LocalDateTime sendTime) {
        this.subject = subject;
        this.content = content;
        this.sendTime = sendTime;
    }

    @Override
    public String toString() {
        return "FarpointMessage{" +
                "subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", sendTime=" + sendTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FarpointMessage that = (FarpointMessage) o;

        if (subject != null ? !subject.equals(that.subject) : that.subject != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        return sendTime != null ? sendTime.equals(that.sendTime) : that.sendTime == null;
    }
}
