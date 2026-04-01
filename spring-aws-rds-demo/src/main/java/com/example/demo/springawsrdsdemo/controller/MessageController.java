package com.example.demo.springawsrdsdemo.controller;

import com.example.demo.springawsrdsdemo.dto.MessageCreateRequest;
import com.example.demo.springawsrdsdemo.dto.MessageResponse;
import com.example.demo.springawsrdsdemo.service.MessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    // 메시지 생성
    @PostMapping
    public ResponseEntity<MessageResponse> create(@Valid @RequestBody MessageCreateRequest req) {
        MessageResponse saved = messageService.create(req);
        return ResponseEntity.created(URI.create("/messages/" + saved.getId())).body(saved);
    }

    // 전체 조회
    @GetMapping
    public ResponseEntity<List<MessageResponse>> findAll() {
        return ResponseEntity.ok(messageService.findAll());
    }
}

