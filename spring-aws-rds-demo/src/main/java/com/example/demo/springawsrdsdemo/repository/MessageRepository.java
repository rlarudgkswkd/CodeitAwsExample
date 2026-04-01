package com.example.demo.springawsrdsdemo.repository;

import com.example.demo.springawsrdsdemo.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}

