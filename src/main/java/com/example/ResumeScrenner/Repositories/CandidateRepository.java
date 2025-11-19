package com.example.ResumeScrenner.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ResumeScrenner.dao.CandidateDao;



public interface CandidateRepository extends JpaRepository<CandidateDao,Long>{
    List<CandidateDao> findByRoom_Id(Long id);
}
