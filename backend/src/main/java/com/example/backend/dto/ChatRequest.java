package com.example.backend.dto;

import lombok.Data;

@Data
public class ChatRequest {
    private String conversationId;
    private String userMessage;
}