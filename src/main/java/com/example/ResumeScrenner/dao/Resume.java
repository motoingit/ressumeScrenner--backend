package com.example.ResumeScrenner.dao;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(
    name = "resumes",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "unique_candidate_room",
            columnNames = {"candidate_id", "room_id", "uploaded_by"}
        )
    }
)
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private String filePath;

   
    @Column(name = "uploaded_by")
    private String uploadedBy;

    @Column(name = "candidate_id")
    private Long candidateId;   

    @Column(name = "manager_id")
    private Long managerId;   

    @Column(name = "room_id")
    private Long roomId;
}
