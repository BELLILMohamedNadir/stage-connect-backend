package com.example.stageconnect.messaging.room;

import com.example.stageconnect.messaging.message.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/room")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping("/start")
    public ResponseEntity<UUID> startChat(@RequestParam Long studentId, @RequestParam Long recruiterId) {
        UUID chatId = roomService.getOrCreateRoom(studentId, recruiterId);
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

    @GetMapping("/{roomId}/messages")
    public ResponseEntity<List<MessageDto>> getMessages(@PathVariable("roomId") UUID roomId) {
        return ResponseEntity.ok(roomService.getMessages(roomId));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<RoomDto>> getRooms(@PathVariable("userId") Long studentId) {
        return ResponseEntity.ok(roomService.getRooms(studentId));
    }
}

