package com.example.ResumeScrenner.dao;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.example.ResumeScrenner.dao.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Setter
public class Userprofile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String email;
    private String name;
    private String password;
    private String image;

    @Enumerated(EnumType.STRING)
    private Role role;
    @JsonIgnore
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private ManagerDao manager;
     @JsonIgnore
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private CandidateDao candidate;
}
