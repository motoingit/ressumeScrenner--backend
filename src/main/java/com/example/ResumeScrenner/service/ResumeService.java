package com.example.ResumeScrenner.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.example.ResumeScrenner.payload.ResumeResponseDto;

public interface ResumeService {
     ResumeResponseDto uploadResume(MultipartFile file,
            Long roomId,
            Long candidateId,
            Long managerId,
            String uploadedBy)throws IOException;
}
