package com.example.ResumeScrenner.service;

import com.example.ResumeScrenner.payload.LoginResponseDto;
import com.example.ResumeScrenner.payload.LoginRequestDto;
import com.example.ResumeScrenner.payload.RegisterManagerRequestDto;

public interface AuthService {
    LoginResponseDto login(LoginRequestDto dto);

    Long registerManager(RegisterManagerRequestDto dto);

}
