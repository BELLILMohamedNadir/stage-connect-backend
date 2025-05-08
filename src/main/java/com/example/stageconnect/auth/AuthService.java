package com.example.stageconnect.auth;


import com.example.stageconnect.auth.helper.AuthenticationRequest;
import com.example.stageconnect.auth.helper.AuthenticationResponse;
import org.springframework.web.multipart.MultipartFile;

public interface AuthService {
    String register(AuthDto authDto, MultipartFile file) throws Exception;
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
