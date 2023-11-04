package com.msgminisystem2.msgminisystem2.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Document(collection = "messages")
public class Message {

    @Id
    private String id;

    private String text;
    private MessageType messageType;
    private LocalDateTime createdAt;
    // while running purpose
    private LocalDateTime lastTimePlacedInQueue;
    private int durationInSeconds;

    public Message() {
    }

    public Message(String text, MessageType messageType, LocalDateTime createdAt, int durationInSeconds) {
        this.text = text;
        this.messageType = messageType;
        this.createdAt = createdAt;
        this.durationInSeconds = durationInSeconds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getLastTimePlacedInQueue() {
        return lastTimePlacedInQueue;
    }

    public void setLastTimePlacedInQueue(LocalDateTime lastTimePlacedInQueue) {
        this.lastTimePlacedInQueue = lastTimePlacedInQueue;
    }

    public int getDurationInSeconds() {
        return durationInSeconds;
    }

    public void setDurationInSeconds(int durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
    }


    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", messageType=" + messageType +
                ", createdAt=" + createdAt +
                ", durationLeftInSeconds=" + durationInSeconds +
                '}';
    }
}
