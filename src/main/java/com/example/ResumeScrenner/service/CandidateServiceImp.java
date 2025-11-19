package com.example.ResumeScrenner.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.ResumeScrenner.Repositories.CandidateRepository;
import com.example.ResumeScrenner.dao.CandidateDao;
import com.example.ResumeScrenner.payload.CandidateResponse;

import lombok.RequiredArgsConstructor;
  @Service
@RequiredArgsConstructor
public class CandidateServiceImp implements CandidateService{
  
     @Autowired
    CandidateRepository candidateRepository;
    @Autowired
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public CandidateDao uploadCandidate(CandidateDao candidate) {
        CandidateDao saved = candidateRepository.save(candidate);
        kafkaTemplate.send("resume-uploaded", saved.toString());
        return saved;
    }

    public List<CandidateDao> getCandidatesByRoom(Long roomId) {
        return candidateRepository.findByRoom_Id(roomId);
    }

    @Override
    public CandidateDao updateRanking(Long id, Double score) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateRanking'");
    }

    @Override
    public CandidateDao updateVerification(Long id, String status) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateVerification'");
    }

 /* public CandidateDao updateRanking(Long id, Double score) {
        CandidateDao candidate = candidateRepository.findById(id).orElseThrow();
        candidate.setRankingScore(score);
        return candidateRepository.save(candidate);
    }*/   

  /*   public CandidateDao updateVerification(Long id, String status) {
        CandidateDao candidate = candidateRepository.findById(id).orElseThrow();
        candidate.setVerificationStatus(status);
        return candidateRepository.save(candidate);
    }*/
}

