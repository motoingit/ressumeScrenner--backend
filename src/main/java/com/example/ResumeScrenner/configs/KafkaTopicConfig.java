package com.example.ResumeScrenner.configs;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic resumeUplaodedTopic(){
      return TopicBuilder.name("resume-uploaded").partitions(3).replicas(1).build();
    }
    @Bean
      public NewTopic roomCreatedTopic(){
      return TopicBuilder.name("room-created").partitions(3).replicas(1).build();
    }
     @Bean
      public NewTopic ManagerUploadedCreatedTopic(){
      return TopicBuilder.name("manager-resume-uploaded").partitions(3).replicas(1).build();
    }
}
