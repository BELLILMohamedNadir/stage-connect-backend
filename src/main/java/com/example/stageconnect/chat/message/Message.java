package com.example.stageconnect.chat.message;

import com.example.stageconnect.chat.room.Room;
import com.example.stageconnect.user.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue
    private Long id;

    private String content;

    private LocalDateTime timestamp;

    @ManyToOne
    private Room room;

    @Enumerated(EnumType.STRING)
    private Role sender;
}
