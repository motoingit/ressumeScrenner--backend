package com.example.ResumeScrenner.service;

import java.util.List;

import com.example.ResumeScrenner.payload.UserprofileDto;
import com.example.ResumeScrenner.payload.UserprofileResponseDto;

public interface UserprofileService {
   public List<UserprofileResponseDto> getallUsers();
    public UserprofileResponseDto postUser(UserprofileDto userprofileDto);
     public UserprofileResponseDto deleteUser(Long id);
     public UserprofileResponseDto getUser(Long id);
      public UserprofileResponseDto updateUser(UserprofileDto userprofileDto,Long id);
}
