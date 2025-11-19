package com.example.ResumeScrenner.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ResumeScrenner.Repositories.ManagerRepository;
import com.example.ResumeScrenner.Repositories.UserRepository;
import com.example.ResumeScrenner.dao.ManagerDao;
import com.example.ResumeScrenner.dao.UserProfile;
import com.example.ResumeScrenner.payload.ManagerResponse;
import com.example.ResumeScrenner.payload.ManagerDto;
import com.example.ResumeScrenner.payload.UserprofileResponseDto;

@Service
public class ManagerServiceImp implements ManagerService {

   @Autowired
   ManagerRepository managerRepository;

   @Autowired
   ModelMapper modelmapper;

   @Override
   public List<ManagerResponse> getallUsers() {
      List<ManagerDao> userprofiles = managerRepository.findAll();
      List<ManagerResponse> ManagerResponse = userprofiles.stream()
            .map(userobj -> modelmapper.map(userobj, ManagerResponse.class)).collect(Collectors.toList());
      return ManagerResponse;
   }

   @Override
   public ManagerResponse postUser(ManagerDto ManagerDto) {
      ManagerDao ManagerDao = modelmapper.map(ManagerDto, ManagerDao.class);
      ManagerDao = managerRepository.save(ManagerDao);
      ManagerResponse ManagerResponse = modelmapper.map(ManagerDao, ManagerResponse.class);
      return ManagerResponse;
   }

   @Override
   public ManagerResponse deleteUser(Long id) {
      Optional<ManagerDao> ManagerDao = managerRepository.findById(id);
      managerRepository.deleteById(id);
      ManagerResponse responseDto = modelmapper.map(ManagerDao, ManagerResponse.class);
      return responseDto;
   }

   @Override
   public ManagerResponse getUser(Long id) {
      Optional<ManagerDao> ManagerDao = managerRepository.findById(id);
      ManagerResponse ManagerResponse = modelmapper.map(ManagerDao, ManagerResponse.class);
      return ManagerResponse;
   }

   @Override
   public ManagerResponse updateUser(ManagerDto ManagerDto, Long id) {
      Optional<ManagerDao> optionalManagerDao = managerRepository.findById(id);
      if (!optionalManagerDao.isPresent()) {
         return null;
      }
      ManagerDao ManagerDao2 = optionalManagerDao.get();

      // Update user profile (name, email) through the relationship
      if (ManagerDao2.getUser() != null) {
         ManagerDao2.getUser().setName(ManagerDto.getName());
         ManagerDao2.getUser().setEmail(ManagerDto.getEmail());
      }

      ManagerDao2 = managerRepository.save(ManagerDao2);
      ManagerResponse ManagerResponse = modelmapper.map(ManagerDao2, ManagerResponse.class);
      return ManagerResponse;
   }

}
