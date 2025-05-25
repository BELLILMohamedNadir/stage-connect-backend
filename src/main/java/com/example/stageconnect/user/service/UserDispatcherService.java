package com.example.stageconnect.user.service;

import com.example.stageconnect.user.ROLE;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserDispatcherService {

    private final Map<ROLE, UserService> services;

    public UserDispatcherService(StudentServiceImpl studentService,
                                 RecruiterServiceImpl recruiterService,
                                 EstablishmentServiceImpl establishmentService) {
        this.services = Map.of(
                ROLE.STUDENT, studentService,
                ROLE.RECRUITER, recruiterService,
                ROLE.ESTABLISHMENT, establishmentService
        );
    }

    public UserService getService(ROLE role) {
        return services.get(role);
    }
}
