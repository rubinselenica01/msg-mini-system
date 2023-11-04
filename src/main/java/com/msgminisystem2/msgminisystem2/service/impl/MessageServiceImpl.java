package com.msgminisystem2.msgminisystem2.service.impl;

import com.msgminisystem2.msgminisystem2.model.dto.MessageRequest;
import com.msgminisystem2.msgminisystem2.model.dto.MessageResponse;
import com.msgminisystem2.msgminisystem2.model.entity.Message;
import com.msgminisystem2.msgminisystem2.model.entity.MessageType;
import com.msgminisystem2.msgminisystem2.model.mapper.MessageMapper;
import com.msgminisystem2.msgminisystem2.repository.MessagesRepository;
import com.msgminisystem2.msgminisystem2.service.MessageService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class MessageServiceImpl implements MessageService {

    //ne startimin e applikacionit gjej te gjitha mesazhet e skeduluara
    private final MessagesRepository messagesRepository;

    private LinkedList<Message> messages;

    private int secondsIfInterrupted ;

    @PostConstruct
    public void initializeListOfMessages(){
        messages = findScheduled();
        secondsIfInterrupted = messages.get(0).getMessageType().getDurationInSeconds();
    }

    @Autowired
    public MessageServiceImpl(MessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
    }


    @Override
    public MessageResponse createNewMessage(MessageRequest messageRequest) {
        Message message = MessageMapper.toEntity(messageRequest);
        messagesRepository.save(message);
        int j=0;
        for(int i=0;i<messages.size(); i++){
            if(message.getMessageType().getPriority()>messages.get(j).getMessageType().getPriority()){

                if(i==0){
                    //ruan sekondat qe do mesazhi qe po nderprehet momentalisht
                    getFirstOfQueue();
                    secondsIfInterrupted = message.getDurationInSeconds();
                }
                messages.add(i,message);
                return MessageMapper.toResponse(message);
            }
            ++j;
        }
        messages.addLast(message);
        return MessageMapper.toResponse(message);
    }

    @Override
    public MessageResponse getFirstOfQueue() {
        Message message = messages.get(0);


        //gjen sekondat e mbetura te cdo mesazhi
        int secondsLeft = ((int) Duration.between(message.getLastTimePlacedInQueue(),LocalDateTime.now()).getSeconds()
                - secondsIfInterrupted) * -1;

        message.setDurationInSeconds(secondsLeft);

        if(secondsLeft<0){
            //gjen nese eshte nderprere mesazhi
            secondsIfInterrupted=(Math.min(messages.get(1).getDurationInSeconds(), messages.get(1).getMessageType().getDurationInSeconds()));
            //nese nuk eshte mesazh i skeduluar, hiqe nga lista fare, ne te kundert fute te fundit
            if(!message.getMessageType().equals(MessageType.SCHEDULED)){
                messages.pop();
            }else{
                Message message1 = messages.pop();
                //riseton duration
                message1.setDurationInSeconds(message1.getMessageType().getDurationInSeconds());
                messages.addLast(message1);
            }
            // i vendos aktualit(pasardhesit) oren e tanishme
            messages.getFirst().setLastTimePlacedInQueue(LocalDateTime.now());
            return MessageMapper.toResponse(messages.getFirst());
        }

        return MessageMapper.toResponse(message);
    }


    @Override
    public LinkedList<MessageResponse> getCurrentQueue() {
        //tregon edhe radhen me sekondate qe levizin
        getFirstOfQueue();
        return messages.stream()
                .map(MessageMapper::toResponse)
                .collect(Collectors.toCollection(LinkedList::new));
    }


    public LinkedList<Message> findScheduled(){
        List<Message> messages1 =  messagesRepository.findAllByMessageType(MessageType.SCHEDULED);
                messages1.forEach(el-> el.setLastTimePlacedInQueue(LocalDateTime.now()));
                messagesRepository.saveAll(messages1);
                return new LinkedList<>(messages1);
    }


    @PreDestroy
    public void destroyNonScheduled(){
        messagesRepository.deleteAllByMessageTypeIsNot(MessageType.SCHEDULED);
    }
}
