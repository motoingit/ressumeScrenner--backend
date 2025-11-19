package com.example.ResumeScrenner.configs.securityconfig;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.ResumeScrenner.Repositories.UserRepository;
import com.example.ResumeScrenner.dao.UserProfile;

@Component
public class CustomUserDetailService implements UserDetailsService{
    @Autowired
      UserRepository userrepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Optional<UserProfile> info=userrepository.findByName(username);
       return info.map(UserInfoUserDetails :: new)
                 .orElseThrow(()-> new UsernameNotFoundException("user not foound"));
     //  if(username==null) throw new UnsupportedOperationException("Unimplemented method 'loadUserByUsername'");
       //else return new UserInfoUserDetails(info);
    }

}
