package com.example.ResumeScrenner.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ResumeScrenner.payload.RoomDto;
import com.example.ResumeScrenner.payload.RoomResponseDto;
import com.example.ResumeScrenner.service.RoomService;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;





@RestController
@RequestMapping("/api/rooms")

public class RoomController {
    @Autowired
     RoomService roomService;

    @PostMapping
    public ResponseEntity<RoomResponseDto> createRoom(@RequestBody RoomDto room) {
        return ResponseEntity.ok(roomService.createRoom(room));
    }

    @GetMapping("/{managerId}")
    public ResponseEntity<List<RoomResponseDto>> getRooms(@PathVariable Long managerId) {
        return ResponseEntity.ok(roomService.getRoomsByManager(managerId));
    }

    @GetMapping("/findbyid/{id}")
    public ResponseEntity<RoomResponseDto> findById(@PathVariable Long id) {
        return new ResponseEntity<RoomResponseDto>(roomService.findbyid(id),HttpStatus.OK);
    }
    @GetMapping("path")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    
    
}
