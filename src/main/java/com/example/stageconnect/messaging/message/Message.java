package com.example.stageconnect.messaging.message;

import com.example.stageconnect.messaging.room.Room;
import com.example.stageconnect.user.ROLE;
import com.example.stageconnect.user.model.BaseUser;
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

    @ManyToOne
    private BaseUser sender;
}
