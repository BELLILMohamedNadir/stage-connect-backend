package com.example.stageconnect.chat.message;

import com.example.stageconnect.user.Role;
import lombok.Data;

@Data
public class MessageRequest {
    private Long roomId;
    private String content;
    private Role sender;
}