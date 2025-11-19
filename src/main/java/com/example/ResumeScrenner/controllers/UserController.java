package com.example.ResumeScrenner.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.ResumeScrenner.Repositories.UserRepository;
import com.example.ResumeScrenner.configs.securityconfig.UserInfoUserDetails;
import com.example.ResumeScrenner.dao.UserProfile;
import com.example.ResumeScrenner.jwt.Jwtutils;
import com.example.ResumeScrenner.payload.UserProfileDto;
import com.example.ResumeScrenner.payload.UserprofileResponseDto;
import com.example.ResumeScrenner.service.UserprofileService;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CookieValue;
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
    AuthenticationManager authenticationManager;

    @Autowired
    Jwtutils jwtutils;

@Autowired
    UserprofileService userprofileService;
    
    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;
  @GetMapping("/private/user")
  public ResponseEntity <List<UserprofileResponseDto>> getAllUsers() {
      return new ResponseEntity<List<UserprofileResponseDto>>(userprofileService.getallUsers(),HttpStatus.OK);
  }
  @PostMapping("/public/user")
  public ResponseEntity<UserprofileResponseDto> postUsers(@RequestBody UserProfileDto userprofileDto) {
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
  public ResponseEntity<UserprofileResponseDto> updateUser(@PathVariable Long id, @RequestBody UserProfileDto userprofileDto) {
      return  new ResponseEntity<UserprofileResponseDto>(userprofileService.updateUser(userprofileDto,id),HttpStatus.OK);
  }
    @PostMapping("/public/user/login")
    public ResponseEntity<?> loginAuth(@RequestBody UserProfileDto userprofileDto,
                                       HttpServletResponse response) {
                                        System.out.println("RAW: " + userprofileDto.getPassword());

UserProfile user = userRepository.findByName(userprofileDto.getName()).get();
System.out.println("DB HASH: " + user.getPassword());

System.out.println("MATCHES? => " +
    passwordEncoder.matches(userprofileDto.getPassword(), user.getPassword()));

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userprofileDto.getName(),
                            userprofileDto.getPassword()
                    )
            );

            if (!authentication.isAuthenticated()) {
                throw new UsernameNotFoundException("Invalid username or password");
            }

            UserInfoUserDetails userDetails = (UserInfoUserDetails) authentication.getPrincipal();
            String token = jwtutils.generateToken(userDetails.getUsername());

            ResponseCookie cookie = ResponseCookie.from("jwt", token)
                     .httpOnly(true)
        .secure(false)
        .path("/")
        .maxAge(7 * 24 * 60 * 60)
        .sameSite("None")
        .build();

             Map<String, Object> body = new HashMap<>();
        body.put("id", user.getUserId());
        body.put("username", user.getName());
        body.put("role", user.getRole());
        body.put("manager", user.getManager());   
        body.put("candidate", user.getCandidate()); 


            return ResponseEntity.ok()
                    .header("Set-Cookie", cookie.toString())
                    .body(body);

        } catch (Exception e) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }
@PostMapping("/public/user/register")
public ResponseEntity<?> registerUser(@RequestBody UserProfileDto userprofileDto) {

    
    String encodedPass = passwordEncoder.encode(userprofileDto.getPassword());
    userprofileDto.setPassword(encodedPass);

    UserprofileResponseDto createdUser = userprofileService.postUser(userprofileDto);

    String token = jwtutils.generateToken(createdUser.getName());

    
    ResponseCookie cookie = ResponseCookie.from("jwt", token)
            .httpOnly(true)
            .secure(true)           
            .path("/")
            .maxAge(7 * 24 * 60 * 60) 
            .sameSite("Lax")
            .build();

   
    Map<String, Object> body = new HashMap<>();
    body.put("id", createdUser.getUserid());
    body.put("username", createdUser.getName());
    body.put("role", createdUser.getRole());

    return ResponseEntity.ok()
            .header("Set-Cookie", cookie.toString())
            .body(body);
}

@PostMapping("/jwt/verify")
public ResponseEntity<?> checkAuth(
        @CookieValue(name = "jwt", required = false) String token) {

    if (token == null) {
        return ResponseEntity.status(401).body("Not authenticated");
    }

    try {
        String username = jwtutils.extractUsernameByToken(token);

        UserInfoUserDetails userDetails =
                (UserInfoUserDetails) userDetailsService.loadUserByUsername(username);

        if (!jwtutils.isValidToken(token, userDetails)) {
            return ResponseEntity.status(401).body("Invalid token");
        }

        UserProfile user = userRepository.findByName(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Map<String, Object> body = new HashMap<>();
        body.put("id", user.getUserId());
        body.put("username", user.getName());
        body.put("role", user.getRole());
        body.put("manager", user.getManager());   
        body.put("candidate", user.getCandidate()); 

        return ResponseEntity.ok(body);

    } catch (Exception e) {
        return ResponseEntity.status(401).body("Invalid or expired token");
    }
}


    @PostMapping("/logout")
    public ResponseEntity<?> logout() {

        ResponseCookie cookie = ResponseCookie.from("jwt", "")
                 .httpOnly(true)
        .secure(true)
        .path("/")
        .maxAge(0)
        .sameSite("None")
        .build();

        Map<String, String> res = new HashMap<>();
        res.put("message", "Logged out successfully");

        return ResponseEntity.ok()
                .header("Set-Cookie", cookie.toString())
                .body(res);
    }
}



