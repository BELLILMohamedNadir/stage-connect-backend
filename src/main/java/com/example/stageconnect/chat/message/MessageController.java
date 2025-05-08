package com.example.stageconnect.chat.message;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MessageController {

    private final MessageServiceImpl chatService;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(MessageRequest message) {
        MessageDto savedMessage = chatService.sendMessage(
                message.getRoomId(),
                message.getContent(),
                message.getSender()
        );
        messagingTemplate.convertAndSend("/topic/chat/" + message.getRoomId(), savedMessage);
    }
}