package com.msgminisystem2.msgminisystem2.model.entity;

import java.util.Arrays;

public enum MessageType {
    SCHEDULED("SCHEDULED",1,10),
    HEAVY_RAIN("HEAVY RAIN",2,15),
    HEAVY_TRAFFIC("HEAVY TRAFFIC",3,20),
    ACCIDENT("ACCIDENT",4,40);

    private final String description;
    private final int priority;
    private final int durationInSeconds;

    MessageType(String description, int priority, int durationInSeconds) {
        this.description = description;
        this.priority = priority;
        this.durationInSeconds = durationInSeconds;
    }

    public static MessageType fromValue(String description){
        return Arrays.stream(MessageType.values())
                .filter(mt -> mt.description.equalsIgnoreCase(description))
                .findFirst().get();
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    public int getDurationInSeconds() {
        return durationInSeconds;
    }
}
