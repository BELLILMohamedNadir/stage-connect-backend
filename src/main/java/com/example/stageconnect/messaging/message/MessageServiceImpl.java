package com.example.stageconnect.messaging.message;

import com.example.stageconnect.messaging.room.Room;
import com.example.stageconnect.messaging.room.RoomRepository;
import com.example.stageconnect.user.model.BaseUser;
import com.example.stageconnect.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final RoomRepository roomRepository;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final MessageMapper messageMapper;


    @Override
    public MessageDto sendMessage(UUID roomId, String content, Long userId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        BaseUser user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("user not found"));

        Message message = Message.builder()
                .room(room)
                .content(content)
                .sender(user)
                .timestamp(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
                .build();
        Message savedMessage = messageRepository.save(message);
        return messageMapper.mapFrom(savedMessage);
    }
}
