package com.example.demo.springawsrdsdemo2.dto;

import java.time.Instant;

public record FileResponseDto(
        String key,
        String url,
        long size,
        Instant lastModified
) {}
