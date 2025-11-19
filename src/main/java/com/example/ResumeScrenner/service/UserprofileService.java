package com.example.ResumeScrenner.service;

import java.util.List;

import com.example.ResumeScrenner.payload.UserProfileDto;
import com.example.ResumeScrenner.payload.UserprofileResponseDto;

public interface UserprofileService {
   public List<UserprofileResponseDto> getallUsers();
    public UserprofileResponseDto postUser(UserProfileDto userprofileDto);
     public UserprofileResponseDto deleteUser(Long id);
     public UserprofileResponseDto getUser(Long id);
      public UserprofileResponseDto updateUser(UserProfileDto userprofileDto,Long id);
}
