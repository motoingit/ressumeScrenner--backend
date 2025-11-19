package com.example.ResumeScrenner.service;

import java.util.List;

import com.example.ResumeScrenner.payload.ManagerDto;
import com.example.ResumeScrenner.payload.ManagerResponse;
import com.example.ResumeScrenner.payload.UserProfileDto;


public interface  ManagerService {
     public List<ManagerResponse> getallUsers();
    public ManagerResponse postUser(ManagerDto ManagerDto);
     public ManagerResponse deleteUser(Long id);
     public ManagerResponse getUser(Long id);
      public ManagerResponse updateUser(ManagerDto managerDto,Long id);
}
