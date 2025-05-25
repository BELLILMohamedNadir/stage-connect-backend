package com.example.stageconnect.messaging.message;

import lombok.Data;

import java.util.UUID;

@Data
public class MessageRequest {
    private UUID roomId;
    private String content;
    private Long senderId;
}