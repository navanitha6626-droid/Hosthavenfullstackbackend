package com.example.hosthaven2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hosthaven2.entity.StayReviewEntity;

@Repository
public interface StayReviewRepository extends JpaRepository<StayReviewEntity, Long> {

}
