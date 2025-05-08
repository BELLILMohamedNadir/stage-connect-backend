package com.example.stageconnect.user.service;


import java.util.List;

public interface UserService <T>{
    T findById(Long id);
    List<T> findAll();
    T update(Long id, T dto);
    void delete(Long id);
}
