package com.msgminisystem2.msgminisystem2.service;

import com.msgminisystem2.msgminisystem2.model.dto.MessageRequest;
import com.msgminisystem2.msgminisystem2.model.dto.MessageResponse;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public interface MessageService {

    MessageResponse createNewMessage(MessageRequest messageRequest);

    MessageResponse getFirstOfQueue();

    LinkedList<MessageResponse> getCurrentQueue();

}
