package com.example.ResumeScrenner.service;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.ResumeScrenner.Repositories.ManagerRepository;
import com.example.ResumeScrenner.Repositories.RoomRepository;
import com.example.ResumeScrenner.dao.ManagerDao;
import com.example.ResumeScrenner.dao.Room;
import com.example.ResumeScrenner.payload.RoomDto;
import com.example.ResumeScrenner.payload.RoomResponseDto;


    @Service
public class RoomServiceImp implements RoomService {
     @Autowired
     ModelMapper modelMapper;
       @Autowired
   RoomRepository roomRepository;
     @Autowired
   ManagerRepository managerRepository;
@Autowired
@Qualifier("roomKafkaTemplate")
private KafkaTemplate<String, Object> roomKafkaTemplate;


    @Override
    public RoomResponseDto createRoom(RoomDto dto) {

       
        ManagerDao manager = managerRepository.findById(dto.getManagerId())
                .orElseThrow(() -> new RuntimeException("Manager not found"));

        Room room = new Room();
        room.setRoomName(dto.getRoomName());
        room.setDescription(dto.getDescription());
        room.setRequiredExperience(dto.getRequiredExperience());
        room.setRequiredSkills(dto.getRequiredSkills());
        room.setLocation(dto.getLocation());
        room.setCreatedBy(manager);
        room.setActive(true);
        room.setCreatedAt(LocalDateTime.now());

        Room saved = roomRepository.save(room);
   Map<String, Object> event = new HashMap<>();
event.put("roomId", saved.getId());
event.put("roomName", saved.getRoomName());
event.put("description", saved.getDescription());
event.put("experience", saved.getRequiredExperience());
event.put("requiredSkills", saved.getRequiredSkills());
event.put("managerId", manager.getId());

roomKafkaTemplate.send("room-created", event);
     

     
        RoomResponseDto response = new RoomResponseDto();
        response.setId(saved.getId());
        response.setRoomName(saved.getRoomName());
        response.setDescription(saved.getDescription());
        response.setRequiredExperience(saved.getRequiredExperience());
        response.setRequiredSkills(saved.getRequiredSkills());
        response.setLocation(saved.getLocation());
        response.setCreatedAt(saved.getCreatedAt());
        response.setActive(saved.isActive());
        response.setCreatedById(manager.getId());
        response.setCreatedByName(manager.getUser().getName());

        return response;
    }

    @Override
    public List<RoomResponseDto> getRoomsByManager(Long managerId) {
        return roomRepository.findByCreatedBy_Id(managerId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private RoomResponseDto mapToResponse(Room room) {
        RoomResponseDto dto = new RoomResponseDto();
        dto.setId(room.getId());
        dto.setRoomName(room.getRoomName());
        dto.setDescription(room.getDescription());
        dto.setRequiredExperience(room.getRequiredExperience());
        dto.setRequiredSkills(room.getRequiredSkills());
        dto.setLocation(room.getLocation());
        dto.setActive(room.isActive());
        dto.setCreatedAt(room.getCreatedAt());
        dto.setCreatedById(room.getCreatedBy().getId());
        dto.setCreatedByName(room.getCreatedBy().getUser().getName());
        return dto;
    }

    @Override
    public RoomResponseDto findbyid(Long id) {
      Room room=roomRepository.findById(id) .orElseThrow(() -> new RuntimeException("Manager not found"));
     return  mapToResponse(room);
    }
}
