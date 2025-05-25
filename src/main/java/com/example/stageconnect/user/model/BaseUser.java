package com.example.stageconnect.user.model;

import com.example.stageconnect.file.File;
import com.example.stageconnect.messaging.message.Message;
import com.example.stageconnect.user.ROLE;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BaseUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Column(nullable = false)
    protected  String name;

    private  String firstName;

    @Column(nullable = false, unique = true)
    @Email(message = "add a valid email")
    protected String email;

    @Column(nullable = false)
    protected String password;

    protected String address;

    @Column(nullable = false, length = 15)
    protected String phone;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    protected ROLE role;

    @Column(nullable = false)
    protected LocalDate dateOfBirth;

    protected  String photo;

    private  String currentPosition;

    protected  String summary;

    @OneToMany(mappedBy = "user")
    private List<File> files;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    private List<Message> messages;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
