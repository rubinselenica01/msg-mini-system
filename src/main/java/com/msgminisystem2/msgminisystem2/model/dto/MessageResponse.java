package com.msgminisystem2.msgminisystem2.model.dto;

import java.time.LocalDateTime;

public class MessageResponse {

    private String text;
    private LocalDateTime timeCreated;
    private int secondsLeft;
    private String description;
    private int priority;


    public MessageResponse() {
    }

    public MessageResponse(String text, LocalDateTime timeCreated, int secondsLeft, String description, int priority) {
        this.text = text;
        this.timeCreated = timeCreated;
        this.secondsLeft = secondsLeft;
        this.description = description;
        this.priority = priority;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    public int getSecondsLeft() {
        return secondsLeft;
    }

    public void setSecondsLeft(int secondsLeft) {
        this.secondsLeft = secondsLeft;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
