package com.example.demo.springawsrdsdemo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class MessageCreateRequest {

    @NotBlank(message = "메시지 내용은 비어 있을 수 없습니다.")
    @Size(max = 500, message = "메시지는 최대 500자까지 입력 가능합니다.")
    private String content;

}
