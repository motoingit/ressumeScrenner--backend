package com.example.ResumeScrenner.Repositories;

import com.example.ResumeScrenner.dao.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResumeRepository extends JpaRepository<Resume, Long> {

    Optional<Resume> findByCandidateIdAndRoomIdAndUploadedBy(
            Long candidateId,
            Long roomId,
            String uploadedBy
    );
}
