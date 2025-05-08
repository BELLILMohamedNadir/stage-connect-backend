package com.example.stageconnect.user.service;

import com.example.stageconnect.user.Role;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserDispatcherService {

    private final Map<Role, UserService> services;

    public UserDispatcherService(StudentServiceImpl studentService,
                                 RecruiterServiceImpl recruiterService,
                                 EstablishmentServiceImpl establishmentService) {
        this.services = Map.of(
                Role.STUDENT, studentService,
                Role.RECRUITER, recruiterService,
                Role.ESTABLISHMENT, establishmentService
        );
    }

    public UserService getService(Role role) {
        return services.get(role);
    }
}
