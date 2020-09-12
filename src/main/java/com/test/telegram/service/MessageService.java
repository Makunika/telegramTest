package com.test.telegram.service;


import com.test.telegram.data.Message;
import com.test.telegram.repo.MessageRepo;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final MessageRepo messageRepo;

    public MessageService(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    public Message findById(int id) {
        return messageRepo.findById(id).orElseThrow();
    }

    public Message save(Message message) {
        return messageRepo.save(message);
    }
}
