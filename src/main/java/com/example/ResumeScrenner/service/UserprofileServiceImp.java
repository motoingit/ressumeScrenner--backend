package com.example.ResumeScrenner.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ResumeScrenner.Repositories.UserRepository;
import com.example.ResumeScrenner.dao.CandidateDao;
import com.example.ResumeScrenner.dao.ManagerDao;
import com.example.ResumeScrenner.dao.Role;
import com.example.ResumeScrenner.dao.Userprofile;
import com.example.ResumeScrenner.payload.UserprofileDto;
import com.example.ResumeScrenner.payload.UserprofileResponseDto;

@Service
public class UserprofileServiceImp implements UserprofileService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    
    @Override
    public List<UserprofileResponseDto> getallUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserprofileResponseDto.class))
                .collect(Collectors.toList());
    }

   
    @Override
    public UserprofileResponseDto postUser(UserprofileDto dto) {

      
        Userprofile user = modelMapper.map(dto, Userprofile.class);

    
        user = userRepository.save(user);

     
        if (user.getRole() == Role.MANAGER) {
            ManagerDao manager = new ManagerDao();
            manager.setUser(user);
            user.setManager(manager);

        } else if (user.getRole() == Role.CANDIDATE) {
            CandidateDao candidate = new CandidateDao();
            candidate.setUser(user);
            user.setCandidate(candidate);
        }

       
        user = userRepository.save(user);

        return modelMapper.map(user, UserprofileResponseDto.class);
    }

    @Override
    public UserprofileResponseDto deleteUser(Long id) {
        Optional<Userprofile> userOpt = userRepository.findById(id);

        if (userOpt.isEmpty()) return null;

        Userprofile user = userOpt.get();

        UserprofileResponseDto dto = modelMapper.map(user, UserprofileResponseDto.class);

        userRepository.deleteById(id);

        return dto;
    }

    @Override
    public UserprofileResponseDto getUser(Long id) {
        Optional<Userprofile> userOpt = userRepository.findById(id);

        if (userOpt.isEmpty()) return null;

        return modelMapper.map(userOpt.get(), UserprofileResponseDto.class);
    }

   
    @Override
    public UserprofileResponseDto updateUser(UserprofileDto dto, Long id) {

        Optional<Userprofile> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()) return null;

        Userprofile user = userOpt.get();

      
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());

        user = userRepository.save(user);

        return modelMapper.map(user, UserprofileResponseDto.class);
    }
}
