package com.example.ResumeScrenner.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ResumeScrenner.dao.ManagerDao;

@Repository
public interface ManagerRepository extends JpaRepository <ManagerDao,Long> {
    
}
