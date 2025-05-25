package com.example.stageconnect.file;

import com.example.stageconnect.application.Application;
import com.example.stageconnect.user.model.BaseUser;
import com.example.stageconnect.user.model.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "file")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String path;

    private Boolean current;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private BaseUser user;

}
