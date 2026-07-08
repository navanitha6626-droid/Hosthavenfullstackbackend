package com.example.hosthaven2.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hosthaven2.entity.SystemUser;

public interface SystemUserRepo extends JpaRepository<SystemUser,Long> {
    Optional<SystemUser> findByEmail(String email);
} 