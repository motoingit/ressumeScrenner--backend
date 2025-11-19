package com.example.ResumeScrenner.payload;

import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RoomDto {
    
    private String roomName;
    private String description;

    private Integer requiredExperience;     

    private List<String> requiredSkills;    

    private String location;

    private Long managerId;                
}
