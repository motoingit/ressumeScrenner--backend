package com.example.ResumeScrenner.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ResumeScrenner.dao.UserProfile;
@Repository
public interface UserRepository extends JpaRepository<UserProfile,Long>{
    Optional<UserProfile> findByName(String userName);
}
