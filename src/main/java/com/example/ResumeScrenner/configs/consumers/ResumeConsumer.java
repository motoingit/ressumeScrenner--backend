package com.example.ResumeScrenner.configs.consumers;

import java.util.Map;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ResumeConsumer {

    private final RestTemplate restTemplate;

    @KafkaListener(topics = "manager-resume-uploaded", groupId = "resume-screener-group")
    public void handleManagerResumeUploaded(Map<String, Object> message) {

        System.out.println("[JAVA KAFKA CONSUMER] manager resume → " + message);

        
        try {
            restTemplate.postForObject(
                "http://localhost:8000/parse",
                message,      
                Map.class
            );
        } catch (Exception e) {
            System.out.println("[ERROR] Python parser offline → " + e.getMessage());
        }
    }

    @KafkaListener(topics = "resume-uploaded", groupId = "resume-screener-group")
    public void handleCandidateResumeUploaded(Map<String, Object> message) {

        System.out.println("[JAVA KAFKA CONSUMER] candidate resume → " + message);

 
        try {
            restTemplate.postForObject(
                "http://localhost:8000/parse",
                message,       
                Map.class
            );
        } catch (Exception e) {
            System.out.println("[ERROR] Python parser offline → " + e.getMessage());
        }
    }
}
