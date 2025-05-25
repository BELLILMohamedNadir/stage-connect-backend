package com.example.stageconnect.messaging.message;

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
