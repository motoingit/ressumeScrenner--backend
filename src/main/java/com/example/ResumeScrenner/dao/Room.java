package com.example.ResumeScrenner.dao;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "rooms")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomName;              
    private String description;           
    private Integer requiredExperience;   

    @ElementCollection
    @CollectionTable(name = "room_required_skills",
            joinColumns = @JoinColumn(name = "room_id"))
    @Column(name = "skill")
    private List<String> requiredSkills; 

    private String location;              

    private LocalDateTime createdAt;
    private boolean isActive;
     @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private ManagerDao createdBy;
     @JsonIgnore
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<CandidateDao> candidates;
}
