package com.example.hosthaven2.services;


import java.security.Key;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.hosthaven2.entity.SystemusersEntity;
import com.example.hosthaven2.exception.BusinessValidationException;
import com.example.hosthaven2.exception.ResourceNotFoundException;
import com.example.hosthaven2.repository.SystemusersRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class SystemusersServices {

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.exp}")
    private long exp;
    @Autowired
    private SystemusersRepository sysRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Create User
    public SystemusersEntity saveData(SystemusersEntity data) {

        if (data.getUsername() == null || data.getUsername().trim().isEmpty()) {
            throw new BusinessValidationException("Username cannot be empty");
        }

        if (data.getEmail() == null || !data.getEmail().contains("@")) {
            throw new BusinessValidationException("Invalid email address");
        }

        if (data.getPassword() == null || data.getPassword().length() < 6) {
            throw new BusinessValidationException("Password must be at least 6 characters");
        }

        if (sysRepo.existsByUsername(data.getUsername())) {
            throw new BusinessValidationException("Username already exists");
        }

        if (sysRepo.existsByEmail(data.getEmail())) {
            throw new BusinessValidationException("Email already exists");
        }

        // BCrypt Password Encoding
        data.setPassword(passwordEncoder.encode(data.getPassword()));

        return sysRepo.save(data);
    }

    // Get All Users
    public List<SystemusersEntity> getAllData() {
        return sysRepo.findAll();
    }

    // Get User By ID
    public SystemusersEntity getUsersDetails(Long id) {

        return sysRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    // Update User
    public SystemusersEntity updateSystemusers(Long id, SystemusersEntity data) {

        SystemusersEntity viewData = sysRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (data.getUsername() == null || data.getUsername().trim().isEmpty()) {
            throw new BusinessValidationException("Username cannot be empty");
        }

        if (data.getEmail() == null || !data.getEmail().contains("@")) {
            throw new BusinessValidationException("Invalid email address");
        }

        if (data.getPassword() == null || data.getPassword().length() < 6) {
            throw new BusinessValidationException("Password must be at least 6 characters");
        }

        viewData.setUsername(data.getUsername());

        // BCrypt Password Encoding
        viewData.setPassword(passwordEncoder.encode(data.getPassword()));

        viewData.setEmail(data.getEmail());
        viewData.setFullName(data.getFullName());

        return sysRepo.save(viewData);
    }

    // Delete User
    public SystemusersEntity getDelete(Long id) {

        SystemusersEntity user = sysRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        sysRepo.delete(user);

        return user;
    }

    public String generateToken(String email) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + exp))
                .signWith(getKeys())
                .compact();
    }

    private Key getKeys() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

}