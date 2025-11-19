package com.example.ResumeScrenner.service;

import java.util.List;

import com.example.ResumeScrenner.dao.CandidateDao;

public interface CandidateService {
     public CandidateDao uploadCandidate(CandidateDao candidate);
      public List<CandidateDao> getCandidatesByRoom(Long roomId);
       public CandidateDao updateRanking(Long id, Double score);
        public CandidateDao updateVerification(Long id, String status);
}
