package com.example.ResumeScrenner.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ResumeScrenner.dao.Userprofile;
@Repository
public interface UserRepository extends JpaRepository<Userprofile,Long>{
    Optional<Userprofile> findByName(String userName);
}