package com.example.hosthaven2.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class MaintenanceTaskEntity {
    @Id
    private Long id;

    private LocalDate scheduledDate;

    public MaintenanceTaskEntity() {
    }

    public MaintenanceTaskEntity(Long id, LocalDate scheduledDate) {
        this.id = id;
        this.scheduledDate = scheduledDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(LocalDate scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

}
