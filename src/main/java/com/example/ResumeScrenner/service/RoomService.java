package com.example.ResumeScrenner.service;

import java.util.List;

import com.example.ResumeScrenner.payload.RoomDto;
import com.example.ResumeScrenner.payload.RoomResponseDto;

public interface RoomService {
    public RoomResponseDto createRoom(RoomDto room) ;
        public List<RoomResponseDto> getRoomsByManager(Long managerId);
         public RoomResponseDto findbyid(Long id);
}
