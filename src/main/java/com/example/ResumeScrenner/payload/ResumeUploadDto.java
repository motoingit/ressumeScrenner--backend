

package com.example.ResumeScrenner.payload;

import lombok.Data;

@Data
public class ResumeUploadDto {
    private Long roomId;
    private Long candidateId;  
    private Long managerId;   
    private String uploadedBy; 
    
}
