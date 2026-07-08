package com.example.hosthaven2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hosthaven2.entity.PropertyListingEntity;

@Repository
public interface PropertyListingRepository extends JpaRepository<PropertyListingEntity, Long> {

}
