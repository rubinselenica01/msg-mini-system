package com.msgminisystem2.msgminisystem2.repository;

import com.msgminisystem2.msgminisystem2.model.entity.Message;
import com.msgminisystem2.msgminisystem2.model.entity.MessageType;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MessagesRepository extends MongoRepository<Message, String> {

    List<Message> findAllByMessageType(MessageType messageType);

    void deleteAllByMessageTypeIsNot(MessageType messageType);
}
