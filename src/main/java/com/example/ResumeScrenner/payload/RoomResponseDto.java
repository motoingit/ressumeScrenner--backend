package com.example.ResumeScrenner.payload;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class RoomResponseDto {

    private Long id;

    private String roomName;
    private String description;

    private Integer requiredExperience;        
    private List<String> requiredSkills;       
    private String location;

    private Long createdById;
    private String createdByName;

    private LocalDateTime createdAt;
    private boolean active;
}
