package com.example.ResumeScrenner.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.ResumeScrenner.payload.UserprofileDto;
import com.example.ResumeScrenner.payload.UserprofileResponseDto;
import com.example.ResumeScrenner.service.UserprofileService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api")
public class UserController {
@Autowired
    UserprofileService userprofileService;

  @GetMapping("/private/user")
  public ResponseEntity <List<UserprofileResponseDto>> getAllUsers() {
      return new ResponseEntity<List<UserprofileResponseDto>>(userprofileService.getallUsers(),HttpStatus.OK);
  }
  @PostMapping("/public/user")
  public ResponseEntity<UserprofileResponseDto> postUsers(@RequestBody UserprofileDto userprofileDto) {
     return new ResponseEntity<UserprofileResponseDto>(userprofileService.postUser(userprofileDto),HttpStatus.OK);
      
      
  }
  @GetMapping("/admin/user")
  public ResponseEntity<UserprofileResponseDto> getUser(@RequestParam Long id) {
      return new ResponseEntity<UserprofileResponseDto>(userprofileService.getUser(id),HttpStatus.OK);
  }
  
  @DeleteMapping("/admin/user")
  public ResponseEntity<UserprofileResponseDto> deleteUser(@RequestParam Long id) {
     return new ResponseEntity<UserprofileResponseDto>(userprofileService.deleteUser(id),HttpStatus.OK);
  }
  @PutMapping("path/{id}")
  public ResponseEntity<UserprofileResponseDto> updateUser(@PathVariable Long id, @RequestBody UserprofileDto userprofileDto) {
      return  new ResponseEntity<UserprofileResponseDto>(userprofileService.updateUser(userprofileDto,id),HttpStatus.OK);
  }
}

