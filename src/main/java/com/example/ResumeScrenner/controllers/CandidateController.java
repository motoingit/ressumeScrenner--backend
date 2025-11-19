package com.example.ResumeScrenner.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ResumeScrenner.dao.CandidateDao;
import com.example.ResumeScrenner.service.CandidateService;

import lombok.RequiredArgsConstructor;


    @RestController
@RequestMapping("/api/candidates")
@RequiredArgsConstructor
public class CandidateController {
    @Autowired
     CandidateService candidateService;

    @PostMapping
    public ResponseEntity<CandidateDao> uploadCandidate(@RequestBody CandidateDao candidate) {
        return ResponseEntity.ok(candidateService.uploadCandidate(candidate));
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<List<CandidateDao>> getCandidates(@PathVariable Long roomId) {
        return ResponseEntity.ok(candidateService.getCandidatesByRoom(roomId));
    }
}

