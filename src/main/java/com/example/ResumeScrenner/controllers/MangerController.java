package com.example.ResumeScrenner.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ResumeScrenner.payload.ManagerDto;
import com.example.ResumeScrenner.payload.ManagerResponse;
import com.example.ResumeScrenner.payload.UserProfileDto;
import com.example.ResumeScrenner.payload.UserprofileResponseDto;
import com.example.ResumeScrenner.service.ManagerService;
import com.example.ResumeScrenner.service.UserprofileService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api")
public class MangerController {
 @Autowired
  ManagerService managerService;

  @GetMapping("/private/Manager")
  public ResponseEntity <List<ManagerResponse>> getAllUsers() {
      return new ResponseEntity<List<ManagerResponse>>(managerService.getallUsers(),HttpStatus.OK);
  }
  @PostMapping("/public/Manager")
  public ResponseEntity<ManagerResponse> postUsers(@RequestBody ManagerDto managerDto) {
     return new ResponseEntity<ManagerResponse>(managerService.postUser(managerDto),HttpStatus.OK);
      
      
  }
  @GetMapping("/admin/Manager")
  public ResponseEntity<ManagerResponse> getUser(@RequestParam Long id) {
      return new ResponseEntity<ManagerResponse>(managerService.getUser(id),HttpStatus.OK);
  }
  
  @DeleteMapping("/admin/Manager")
  public ResponseEntity<ManagerResponse> deleteUser(@RequestParam Long id) {
     return new ResponseEntity<ManagerResponse>(managerService.deleteUser(id),HttpStatus.OK);
  }
  @PutMapping("/admin/Manager/path/{id}")
  public ResponseEntity<ManagerResponse> updateUser(@PathVariable Long id, @RequestBody ManagerDto managerDto) {
      return  new ResponseEntity<ManagerResponse>(managerService.updateUser(managerDto,id),HttpStatus.OK);
  }
}
    

