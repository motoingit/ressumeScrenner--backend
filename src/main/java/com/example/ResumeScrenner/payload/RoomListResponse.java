package com.example.ResumeScrenner.payload;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class RoomListResponse {
    private List <RoomResponseDto> rooms;
}


