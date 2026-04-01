package com.example.demo.springawsrdsdemo.service;

import com.example.demo.springawsrdsdemo.domain.Message;
import com.example.demo.springawsrdsdemo.dto.MessageCreateRequest;
import com.example.demo.springawsrdsdemo.dto.MessageResponse;
import com.example.demo.springawsrdsdemo.repository.MessageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    // 생성
    public MessageResponse create(MessageCreateRequest req) {
        Message message = new Message(req.getContent());
        Message saved = messageRepository.save(message);

        return new MessageResponse(
                saved.getId(),
                saved.getContent(),
                saved.getCreatedAt()
        );
    }

    // 전체 조회
    @Transactional(readOnly = true)
    public List<MessageResponse> findAll() {
        return messageRepository.findAll()
                .stream()
                .map(m -> new MessageResponse(
                        m.getId(),
                        m.getContent(),
                        m.getCreatedAt()
                ))
                .toList();
    }
}
