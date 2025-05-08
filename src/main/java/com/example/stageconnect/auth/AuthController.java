package com.example.stageconnect.auth;


import com.example.stageconnect.auth.helper.AuthenticationRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService service;

    @PostMapping(path = "/register")
    public ResponseEntity<?> register(@Valid @RequestPart("authDto") String authDtoJson, @RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
        // Create an instance of ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        // Convert the JSON string to a authDto object
        AuthDto authDto = objectMapper.readValue(authDtoJson, AuthDto.class);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.register(authDto, file));
    }

    @PostMapping(path = "/authentication")
    public ResponseEntity<?> authenticate(@Valid @RequestBody AuthenticationRequest request){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.authenticate(request));
    }
}
