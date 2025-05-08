package com.example.stageconnect.chat.message;

import com.example.stageconnect.chat.room.Room;
import com.example.stageconnect.user.Role;
import java.util.List;

public interface MessageService {

    Long getOrCreateChat(Long studentId, Long recruiterId);

    MessageDto sendMessage(Long chatId, String content, Role role);

    List<MessageDto> getMessages(Long roomId);
}
