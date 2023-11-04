package com.msgminisystem2.msgminisystem2.controller;

import com.msgminisystem2.msgminisystem2.model.dto.MessageRequest;
import com.msgminisystem2.msgminisystem2.model.dto.MessageResponse;
import com.msgminisystem2.msgminisystem2.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessagesController {

    private final MessageService messageService;

    @Autowired
    public MessagesController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/new")
    public ResponseEntity<MessageResponse> createNewMessage(@RequestBody
                                                            MessageRequest messageRequest){
        return ResponseEntity.ok(messageService.createNewMessage(messageRequest));
    }

    @GetMapping("/current")
    public ResponseEntity<MessageResponse> getActualMessage(){
        return ResponseEntity.ok(messageService.getFirstOfQueue());
    }

    @GetMapping("/current-queue")
    public ResponseEntity<LinkedList<MessageResponse>> getActualQueue(){
        return ResponseEntity.ok(messageService.getCurrentQueue());
    }
}
