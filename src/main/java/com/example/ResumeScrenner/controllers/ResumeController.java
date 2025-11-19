package com.example.ResumeScrenner.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.ResumeScrenner.payload.ResumeResponseDto;
import com.example.ResumeScrenner.service.ResumeService;

import java.io.IOException;

@RestController
@RequestMapping("/api/resume")
public class ResumeController {

    @Autowired ResumeService resumeService;

    @PostMapping("/upload")
    public ResponseEntity<ResumeResponseDto> upload(
        @RequestParam("file") MultipartFile file,
        @RequestParam("roomId") Long roomId,
        @RequestParam(value = "candidateId", required = false) Long candidateId,
        @RequestParam(value = "managerId", required = false) Long managerId,
        @RequestParam("uploadedBy") String uploadedBy
    ) throws IOException {

        return new ResponseEntity(resumeService.uploadResume(
                file,
                roomId,
                candidateId,
                managerId,
                uploadedBy
        ),HttpStatus.OK);
    }
}


