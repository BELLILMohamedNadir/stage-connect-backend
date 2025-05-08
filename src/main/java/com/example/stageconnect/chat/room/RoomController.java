package com.example.stageconnect.chat.room;

import com.example.stageconnect.chat.message.MessageDto;
import com.example.stageconnect.chat.message.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chats")
@RequiredArgsConstructor
public class RoomController {

    private final MessageService chatService;

    @PostMapping("/start")
    public ResponseEntity<Long> startChat(@RequestParam Long studentId, @RequestParam Long recruiterId) {
        Long chatId = chatService.getOrCreateChat(studentId, recruiterId);
        return ResponseEntity.ok(chatId);
    }

//    @PostMapping("/{chatId}/message")
//    public ResponseEntity<MessageDto> sendMessage(
//            @PathVariable Long chatId,
//            @RequestBody MessageRequest messageRequest
//    ) {
//        MessageDto message = chatService.sendMessage(chatId, messageRequest.getContent(), messageRequest.getSenderType());
//        return ResponseEntity.ok(message);
//    }

    @GetMapping("/{chatId}/messages")
    public ResponseEntity<List<MessageDto>> getMessages(@PathVariable Long chatId) {
        return ResponseEntity.ok(chatService.getMessages(chatId));
    }
}

