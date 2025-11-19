package com.example.ResumeScrenner.payload;



import lombok.Data;

@Data
public class ResumeResponseDto {
    private Long id;
    private String fileName;
    private String filePath;
    private Long candidateId;
    private Long roomId;
     private Long managerId;   
    private String uploadedBy; 
}
