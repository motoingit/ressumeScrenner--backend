package com.example.ResumeScrenner.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
@Configuration
public class Resttempletconfig {
        @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
