package com.example.ResumeScrenner.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter

public class ManagerDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_id")
    private Userprofile user;
  
    @JsonIgnore
    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL)
    private java.util.List<Room> rooms;
}


