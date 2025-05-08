package com.example.stageconnect.auth;


import com.example.stageconnect.auth.helper.AuthenticationRequest;
import com.example.stageconnect.auth.helper.AuthenticationResponse;
import com.example.stageconnect.config.JwtService;
import com.example.stageconnect.file.FileService;
import com.example.stageconnect.offer.Offer;
import com.example.stageconnect.user.*;
import com.example.stageconnect.user.model.BaseUser;
import com.example.stageconnect.user.model.Establishment;
import com.example.stageconnect.user.model.Recruiter;
import com.example.stageconnect.user.model.Student;
import com.example.stageconnect.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final FileService fileService;
    private final AuthMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public String register(AuthDto authDto, MultipartFile file) throws Exception  {

        String encodedPassword = passwordEncoder.encode(authDto.getPassword());
        BaseUser user;

        switch (authDto.getRole()) {
            case STUDENT:
                Establishment establishment = (Establishment) userRepository.findById(authDto.getEstablishmentId())
                        .orElseThrow(() -> new RuntimeException("Establishment not found"));

                user = Student.builder()
                        .email(authDto.getEmail())
                        .password(encodedPassword)
                        .role(Role.STUDENT)
                        .name(authDto.getName())
                        .firstName(authDto.getFirstName())
                        .phone(authDto.getPhone())
                        .gender(authDto.getGender())
                        .dateOfBirth(authDto.getDateOfBirth())
                        .establishment(establishment)
                        .build();
                userRepository.save((Student) user);
                break;

            case RECRUITER:
                user = Recruiter.builder()
                        .email(authDto.getEmail())
                        .password(encodedPassword)
                        .role(Role.RECRUITER)
                        .name(authDto.getName())
                        .phone(authDto.getPhone())
                        .dateOfBirth(authDto.getDateOfBirth())
                        .build();
                userRepository.save((Recruiter) user);
                break;
            case ESTABLISHMENT:
                user = Establishment.builder()
                        .email(authDto.getEmail())
                        .password(encodedPassword)
                        .role(Role.ESTABLISHMENT)
                        .name(authDto.getName())
                        .phone(authDto.getPhone())
                        .dateOfBirth(authDto.getDateOfBirth())
                        .build();
                userRepository.save((Establishment) user);
                break;
            default:
                throw new IllegalArgumentException("Unknown role");
        }

        //update the user with the photo path
        if (!file.isEmpty()) {
            String photoPath = fileService.saveFile(file, user.getId());
            if (photoPath != null) {
                user.setPhoto(photoPath);
                userRepository.save(user);
            }
        }

        return "Registered successfully";
    }


    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        Optional<BaseUser> user = userRepository.findByEmail(request.getEmail());

        // when we're here officially that user exists because .authenticate throws an exception if not
        BaseUser baseUser = user.get();
        String jwtToken = jwtService.generateToken(baseUser);

        switch (baseUser.getRole()) {
            case STUDENT:
                if (baseUser instanceof Student student) {
                    return AuthenticationResponse.builder()
                            .id(student.getId())
                            .firstName(student.getFirstName())
                            .name(student.getName())
                            .email(student.getEmail())
                            .role(student.getRole().name())
                            .phone(student.getPhone())
                            .address(student.getAddress())
                            .gender(student.getGender())
                            .photo(fileService.generateFileUrl(student.getPhoto()))
                            .token(jwtToken)
                            .build();
                }
                throw new IllegalArgumentException("Expected Student, but found: " + baseUser.getClass().getName());

            case RECRUITER:
                if (baseUser instanceof Recruiter recruiter) {
                    return AuthenticationResponse.builder()
                            .id(recruiter.getId())
                            .name(recruiter.getName())
                            .email(recruiter.getEmail())
                            .role(recruiter.getRole().name())
                            .phone(recruiter.getPhone())
                            .address(recruiter.getAddress())
                            .photo(fileService.generateFileUrl(recruiter.getPhoto()))
                            .token(jwtToken)
                            .build();
                }
                throw new IllegalArgumentException("Expected Recruiter, but found: " + baseUser.getClass().getName());

            case ESTABLISHMENT:
                if (baseUser instanceof Establishment establishment) {
                    return AuthenticationResponse.builder()
                            .id(establishment.getId())
                            .name(establishment.getName())
                            .email(establishment.getEmail())
                            .role(establishment.getRole().name())
                            .phone(establishment.getPhone())
                            .address(establishment.getAddress())
                            .photo(fileService.generateFileUrl(establishment.getPhoto()))
                            .token(jwtToken)
                            .build();
                }
                throw new IllegalArgumentException("Expected Establishment, but found: " + baseUser.getClass().getName());

            default:
                throw new IllegalArgumentException("Unknown role");
        }
    }


}
