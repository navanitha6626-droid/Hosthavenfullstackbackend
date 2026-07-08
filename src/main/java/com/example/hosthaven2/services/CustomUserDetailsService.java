package com.example.hosthaven2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.hosthaven2.entity.SystemUser;
import com.example.hosthaven2.exception.ResourceNotFoundException;

import com.example.hosthaven2.repository.SystemUserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    SystemUserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
      SystemUser user = userRepo.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not Found"));

      return User.builder()
             .username(user.getEmail())
             .password(user.getPassword())
             .roles(user.getRole().name())
             .build();
    }
    
}
