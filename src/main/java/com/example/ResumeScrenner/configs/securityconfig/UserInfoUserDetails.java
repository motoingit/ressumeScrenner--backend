package com.example.ResumeScrenner.configs.securityconfig;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.ResumeScrenner.dao.CandidateDao;
import com.example.ResumeScrenner.dao.ManagerDao;
import com.example.ResumeScrenner.dao.Role;
import com.example.ResumeScrenner.dao.Userprofile;

public class UserInfoUserDetails implements UserDetails {

    private Long id;
    private String username;
    private String password;
    private Role role;

    private ManagerDao manager;
    private CandidateDao candidate;

    public UserInfoUserDetails(Userprofile user) {
        this.id = user.getUserId();
        this.username = user.getName();
        this.password = user.getPassword();
        this.role = user.getRole();

   
        if (user.getManager() != null) {
            this.manager = user.getManager();
        }

        if (user.getCandidate() != null) {
            this.candidate = user.getCandidate();
        }
    }

   
    public Long getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }

    public ManagerDao getManagerId() {
        return manager;
    }

    public CandidateDao getCandidateId() {
        return candidate;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }

}
