package com.example.demo.springawsrdsdemo.dto;

import java.time.LocalDateTime;

public class MessageResponse {

    private final Long id;
    private final String content;
    private final LocalDateTime createdAt;

    public MessageResponse(Long id, String content, LocalDateTime createdAt) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
