package com.example.ResumeScrenner.dao;


import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Setter

public class Userprofile {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long userid;
    private String email;
    private String name;
    private String image;

}
