// src/main/java/com/yourapp/model/ChatMessage.java
package com.example.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String conversationId;
    private String sender; // "USER" or "SYSTEM"

    @Column(columnDefinition = "TEXT")
    private String message;

    private LocalDateTime timestamp;
}
