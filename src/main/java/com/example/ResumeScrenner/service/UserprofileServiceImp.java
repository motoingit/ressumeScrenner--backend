package com.example.ResumeScrenner.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ResumeScrenner.Repositories.UserRepository;
import com.example.ResumeScrenner.dao.Userprofile;
import com.example.ResumeScrenner.payload.UserprofileDto;
import com.example.ResumeScrenner.payload.UserprofileResponseDto;

@Service
public class UserprofileServiceImp implements UserprofileService{

   @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelmapper;

    @Override
    public List<UserprofileResponseDto> getallUsers() {
      List<Userprofile> userprofiles=userRepository.findAll();
    List<UserprofileResponseDto> userprofileResponsedto= userprofiles.stream().map(userobj -> modelmapper.map(userobj,UserprofileResponseDto.class)).collect(Collectors.toList());
        return userprofileResponsedto;
    }

    @Override
    public UserprofileResponseDto postUser(UserprofileDto userprofileDto) {
          Userprofile userprofile=modelmapper.map( userprofileDto,Userprofile.class);
             userprofile=userRepository.save(userprofile);
             UserprofileResponseDto userprofileResponseDto=modelmapper.map(userprofile,UserprofileResponseDto.class);
             return userprofileResponseDto;
    }


    @Override
    public UserprofileResponseDto deleteUser(Long id) {
       Optional<Userprofile> userprofile= userRepository.findById(id);
       userRepository.deleteById(id);
       UserprofileResponseDto responseDto=modelmapper.map(userprofile,UserprofileResponseDto.class);
       return responseDto;
    }

    @Override
    public UserprofileResponseDto getUser(Long id) {
       Optional<Userprofile> userprofile=userRepository.findById(id);
       UserprofileResponseDto userprofileResponseDto=modelmapper.map(userprofile,UserprofileResponseDto.class);
       return userprofileResponseDto;
    }

    @Override
    public UserprofileResponseDto updateUser(UserprofileDto  userprofileDto,Long id) {
          Optional< Userprofile> userprofile=userRepository.findById(id);
                 Userprofile userprofile2=modelmapper.map(userprofile,Userprofile.class);
          userprofile2.setName(userprofileDto.getName());
          userprofile2.setImage(userprofileDto.getImage());
          userprofile2.setEmail(userprofileDto.getEmail());
          userprofile2=userRepository.save(userprofile2);
         UserprofileResponseDto userprofileResponseDto= modelmapper.map(userprofile2,UserprofileResponseDto.class);

         return userprofileResponseDto;
      
    }
    

}
