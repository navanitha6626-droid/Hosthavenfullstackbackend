package com.example.hosthaven2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.hosthaven2.entity.SystemusersEntity;

public interface SystemusersRepository extends JpaRepository<SystemusersEntity, Long> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}