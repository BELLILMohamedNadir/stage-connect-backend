package com.example.stageconnect.chat.message;

import com.example.stageconnect.chat.room.Room;
import com.example.stageconnect.chat.room.RoomRepository;
import com.example.stageconnect.user.Role;
import com.example.stageconnect.user.model.Recruiter;
import com.example.stageconnect.user.model.Student;
import com.example.stageconnect.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final RoomRepository chatRepository;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final MessageMapper messageMapper;

    @Override
    public Long getOrCreateChat(Long studentId, Long recruiterId) {
        Room savedChat =  chatRepository.findByStudentIdAndRecruiterId(studentId, recruiterId)
                .orElseGet(() -> {
                    Student student = (Student) userRepository.findById(studentId)
                            .orElseThrow(() -> new RuntimeException("student not found"));
                    Recruiter recruiter = (Recruiter) userRepository.findById(recruiterId)
                            .orElseThrow(() -> new RuntimeException("Recruiter not found"));
                    Room chat = Room.builder()
                            .date(LocalDate.now())
                            .student(student)
                            .recruiter(recruiter)
                            .build();
                    return chatRepository.save(chat);
                });
        return savedChat.getId();
    }

    @Override
    public MessageDto sendMessage(Long roomId, String content, Role role) {
        Room room = chatRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        Message message = Message.builder()
                .room(room)
                .content(content)
                .sender(role)
                .timestamp(LocalDateTime.now())
                .build();
        Message savedMessage = messageRepository.save(message);
        return messageMapper.mapFrom(savedMessage);
    }

    @Override
    public List<MessageDto> getMessages(Long roomId) {
        return messageRepository.findByRoomIdOrderByTimestampAsc(roomId)
                .stream()
                .map(messageMapper::mapFrom)
                .toList();
    }
}
