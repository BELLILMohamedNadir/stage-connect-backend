package com.example.stageconnect.messaging.room;

import com.example.stageconnect.messaging.message.MessageDto;

import java.util.List;
import java.util.UUID;

public interface RoomService {

    UUID getOrCreateRoom(Long studentId, Long recruiterId);

    List<MessageDto> getMessages(UUID roomId);

    List<RoomDto> getRooms(Long studentId);
}
