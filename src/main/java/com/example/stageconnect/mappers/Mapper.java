package com.example.stageconnect.mappers;

import java.io.IOException;

public interface Mapper<P, T> {

    P mapTo(T t);

    T mapFrom(P p) throws IOException;

    void updateEntityFromDto(T dto, P entity);
}
