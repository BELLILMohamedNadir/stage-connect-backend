package com.example.stageconnect.messaging.message;

import com.example.stageconnect.user.ROLE;
import java.util.List;
import java.util.UUID;

public interface MessageService {
    MessageDto sendMessage(UUID roomId, String content, Long senderId);
}
