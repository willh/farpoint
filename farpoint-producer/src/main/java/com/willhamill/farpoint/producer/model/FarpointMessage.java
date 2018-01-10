package com.willhamill.farpoint.producer.model;

import java.io.Serializable;
import java.util.Date;

public final class FarpointMessage implements Serializable {

    private String subject;
    private String content;
    private Date sendTime;

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public FarpointMessage() {
    }

    public FarpointMessage(String subject, String content, Date sendTime) {
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
}
