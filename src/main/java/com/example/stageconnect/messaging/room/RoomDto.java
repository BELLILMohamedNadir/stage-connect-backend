package com.example.stageconnect.messaging.room;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomDto {

    private UUID id;

    private Long senderId;

    private String sender;

    private String logo;

    private String lastMessage;

    private LocalDateTime lastMessageTime;

}
