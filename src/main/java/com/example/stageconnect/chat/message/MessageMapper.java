package com.example.stageconnect.chat.message;

import com.example.stageconnect.education.Education;
import com.example.stageconnect.education.EducationDto;
import com.example.stageconnect.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper implements Mapper<Message, MessageDto> {

    private ModelMapper mapper;

    public MessageMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Message mapTo(MessageDto dto) {
        return mapper.map(dto, Message.class);
    }

    @Override
    public MessageDto mapFrom(Message message) {
        return mapper.map(message, MessageDto.class);
    }

    @Override
    public void updateEntityFromDto(MessageDto dto, Message entity) {
        mapper.map(dto, entity);
    }
}
