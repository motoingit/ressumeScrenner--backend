package com.example.ResumeScrenner.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ResumeScrenner.dao.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room,Long> {
     List<Room> findByCreatedBy_Id(Long managerId);
}
