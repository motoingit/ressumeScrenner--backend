package com.example.ResumeScrenner.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "candidates")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class CandidateDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserProfile user;

    private String githubLink;
    private String linkedinLink;
    private String degree;
    private String resumeUrl;
     @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    private Double rankingScore;

    @Enumerated(EnumType.STRING)
    private VerificationStatus verificationStatus;
}
