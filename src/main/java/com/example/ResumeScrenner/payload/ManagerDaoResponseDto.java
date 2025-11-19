package com.example.ResumeScrenner.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagerDaoResponseDto {
    private Long id;
    private String name;
    private String email;
}
