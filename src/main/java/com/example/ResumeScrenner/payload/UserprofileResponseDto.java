package com.example.ResumeScrenner.payload;

import com.example.ResumeScrenner.dao.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class UserprofileResponseDto {
    private Long userid;
    private String email;
    private String name;
    private String image;
      private Role role;
}
