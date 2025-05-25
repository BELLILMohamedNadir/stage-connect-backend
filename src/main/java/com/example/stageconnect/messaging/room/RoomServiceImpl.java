package com.example.stageconnect.messaging.room;

import com.couchbase.client.core.error.UserNotFoundException;
import com.example.stageconnect.file.FileService;
import com.example.stageconnect.messaging.message.*;
import com.example.stageconnect.user.ROLE;
import com.example.stageconnect.user.model.BaseUser;
import com.example.stageconnect.user.model.Recruiter;
import com.example.stageconnect.user.model.Student;
import com.example.stageconnect.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.example.stageconnect.user.ROLE.RECRUITER;
import static com.example.stageconnect.user.ROLE.STUDENT;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final MessageMapper messageMapper;
    private final FileService fileService;

    @Override
    public UUID getOrCreateRoom(Long studentId, Long recruiterId) {
        Room savedRoom =  roomRepository.findByStudentIdAndRecruiterId(studentId, recruiterId)
                .orElseGet(() -> {
                    Student student = (Student) userRepository.findById(studentId)
                            .orElseThrow(() -> new RuntimeException("student not found"));
                    Recruiter recruiter = (Recruiter) userRepository.findById(recruiterId)
                            .orElseThrow(() -> new RuntimeException("Recruiter not found"));
                    Room room = Room.builder()
                            .date(LocalDate.now())
                            .student(student)
                            .recruiter(recruiter)
                            .build();
                    return roomRepository.save(room);
                });
        return savedRoom.getId();
    }

    @Override
    public List<MessageDto> getMessages(UUID roomId) {
        return messageRepository.findByRoomIdOrderByTimestampAsc(roomId)
                .stream()
                .sorted(Comparator.comparing(Message::getTimestamp))
                .map(messageMapper::mapFrom)
                .toList();
    }

    @Override
    public List<RoomDto> getRooms(Long userId) {
        BaseUser baseUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Room> rooms = List.of();
        ROLE role = baseUser.getRole();
        if (baseUser instanceof Student) {
            rooms = roomRepository.findByStudentId(userId).orElse(List.of());
        }else if (baseUser instanceof Recruiter) {
            rooms = roomRepository.findByRecruiterId(userId).orElse(List.of());
        }
        return rooms.stream()
                .map(room -> {
                    Message lastMessage = room.getMessages().stream()
                            .max(Comparator.comparing(Message::getTimestamp))
                            .orElse(null);
                    BaseUser sender;

                    if (role == STUDENT) {sender = room.getRecruiter();}
                    else {sender = room.getStudent();}

                    if (lastMessage == null) {
                        lastMessage = Message
                                .builder()
                                .content("")
                                .timestamp(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
                                .build();
                    }

                    return RoomDto.builder()
                            .id(room.getId())
                            .senderId(sender.getId())
                            .lastMessage(lastMessage.getContent())
                            .logo(fileService.generateFileUrl(sender.getPhoto()))
                            .sender(sender.getName())
                            .lastMessageTime(lastMessage.getTimestamp())
                            .build();
                })
                .collect(Collectors.toList());

    }
}
