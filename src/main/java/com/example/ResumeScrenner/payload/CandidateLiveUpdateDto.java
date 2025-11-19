package com.example.ResumeScrenner.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CandidateLiveUpdateDto {
    private Long candidateId;
    private Double rankingScore;
    private String verificationStatus;
    private String message; 
}
