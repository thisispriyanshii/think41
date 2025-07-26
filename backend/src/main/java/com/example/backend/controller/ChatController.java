// src/main/java/com/yourapp/controller/ChatController.java
package com.example.backend.controller;

import com.example.backend.dto.ChatRequest;
import com.example.backend.model.ChatMessage;
import com.example.backend.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @PostMapping
    public String handleChat(@RequestBody ChatRequest request) {
        // Save user message
        ChatMessage userMsg = new ChatMessage(null, request.getConversationId(), "USER", request.getUserMessage(), LocalDateTime.now());
        chatMessageRepository.save(userMsg);

        // Dummy response
        String response = "Thanks for your message, we'll get back to you.";

        // Save system response
        ChatMessage systemMsg = new ChatMessage(null, request.getConversationId(), "SYSTEM", response, LocalDateTime.now());
        chatMessageRepository.save(systemMsg);

        return response;
    }
}
