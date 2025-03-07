package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;


@Service
public class MessageService {
    private final MessageRepository messageRepository;
    @Autowired
    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    public Message postMessage(Message message){
        if(message.getMessageText() != "" && message.getMessageText().length() <= 255){
            messageRepository.save(message);
            return message;
        }
        return null;
    }
}
