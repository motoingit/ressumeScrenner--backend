package com.example.ResumeScrenner.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.ResumeScrenner.Repositories.ResumeRepository;
import com.example.ResumeScrenner.dao.Resume;
import com.example.ResumeScrenner.payload.ResumeResponseDto;

@Service
public class ResumeServiceImp implements ResumeService {

    private final String UPLOAD_DIR = "uploads/resumes/";

    @Autowired 
    ResumeRepository resumeRepository;

    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public ResumeResponseDto uploadResume(
            MultipartFile file,
            Long roomId,
            Long candidateId,
            Long managerId,
            String uploadedBy
    ) throws IOException {

        // ❌ Prevent duplicate candidate uploads
        if (uploadedBy.equals("candidate")) {
            resumeRepository.findByCandidateIdAndRoomIdAndUploadedBy(candidateId, roomId, "candidate")
                .ifPresent(r -> {
                    throw new RuntimeException("Candidate already uploaded a resume for this room!");
                });
        }

        // Create storage directory
        File folder = new File(UPLOAD_DIR);
        if (!folder.exists()) folder.mkdirs();

        // Save file
        String storedFileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(UPLOAD_DIR + storedFileName);
        Files.write(filePath, file.getBytes());

        // Save DB entry
        Resume resume = new Resume();
        resume.setFileName(storedFileName);
        resume.setFilePath(filePath.toString());
        resume.setUploadedBy(uploadedBy);
        resume.setCandidateId(candidateId);
        resume.setManagerId(managerId);
        resume.setRoomId(roomId);

        Resume saved = resumeRepository.save(resume);

        // 🔥 Correct Kafka payload
        var kafkaPayload = new java.util.HashMap<String, Object>();
        kafkaPayload.put("filePath", filePath.toString());
        kafkaPayload.put("fileName", storedFileName);
        kafkaPayload.put("roomId", roomId);
        kafkaPayload.put("candidateId", candidateId);
        kafkaPayload.put("managerId", managerId);
        kafkaPayload.put("uploadedBy", uploadedBy);

        // 🔥 Send to correct topic
        String topic = uploadedBy.equals("candidate")
                ? "resume-uploaded"
                : "manager-resume-uploaded";

        kafkaTemplate.send(topic, kafkaPayload);

        // Response
        ResumeResponseDto dto = new ResumeResponseDto();
        dto.setId(saved.getId());
        dto.setFileName(saved.getFileName());
        dto.setFilePath(saved.getFilePath());
        dto.setCandidateId(saved.getCandidateId());
        dto.setManagerId(saved.getManagerId());
        dto.setRoomId(saved.getRoomId());
        dto.setUploadedBy(saved.getUploadedBy());

        return dto;
    }
}
