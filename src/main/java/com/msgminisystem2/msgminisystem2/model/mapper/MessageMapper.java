package com.msgminisystem2.msgminisystem2.model.mapper;

import com.msgminisystem2.msgminisystem2.model.dto.MessageRequest;
import com.msgminisystem2.msgminisystem2.model.dto.MessageResponse;
import com.msgminisystem2.msgminisystem2.model.entity.Message;
import com.msgminisystem2.msgminisystem2.model.entity.MessageType;

import java.time.LocalDateTime;

public class MessageMapper {

    public static Message toEntity(MessageRequest messageRequest){
        Message message = new Message();
        message.setText(messageRequest.getText());
        message.setMessageType(MessageType.fromValue(messageRequest.getDescription()));
        message.setCreatedAt(LocalDateTime.now());
        message.setLastTimePlacedInQueue(LocalDateTime.now());
        message.setDurationInSeconds(message.getMessageType().getDurationInSeconds());
        return message;
    }

    public static MessageResponse toResponse(Message message){
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setText(message.getText());
        messageResponse.setDescription(message.getMessageType().getDescription());
        messageResponse.setTimeCreated(message.getCreatedAt());
        messageResponse.setPriority(message.getMessageType().getPriority());
        messageResponse.setSecondsLeft(message.getDurationInSeconds());
        return messageResponse;
    }
}
