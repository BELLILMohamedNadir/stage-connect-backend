package com.example.stageconnect.auth;

import com.example.stageconnect.user.model.BaseUser;
import com.example.stageconnect.user.model.Establishment;
import com.example.stageconnect.user.model.Recruiter;
import com.example.stageconnect.user.model.Student;
import com.example.stageconnect.user.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        BaseUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        // Handle different roles properly
        switch (user.getRole()) {
            case STUDENT:
                if (user instanceof Student) {
                    return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getAuthorities());
                } else {
                    throw new UsernameNotFoundException("Expected a Student but found: " + user.getClass().getName());
                }
            case RECRUITER:
                if (user instanceof Recruiter) {
                    return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getAuthorities());
                } else {
                    throw new UsernameNotFoundException("Expected a Recruiter but found: " + user.getClass().getName());
                }
            case ESTABLISHMENT:
                if (user instanceof Establishment) {
                    return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getAuthorities());
                } else {
                    throw new UsernameNotFoundException("Expected an Establishment but found: " + user.getClass().getName());
                }
            default:
                throw new UsernameNotFoundException("User role not found for email: " + email);
        }
    }
}



