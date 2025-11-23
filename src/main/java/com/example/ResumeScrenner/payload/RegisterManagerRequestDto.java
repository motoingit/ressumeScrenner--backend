package com.example.ResumeScrenner.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterManagerRequestDto {
    private String name;
    private String email;
    private String password;
}
