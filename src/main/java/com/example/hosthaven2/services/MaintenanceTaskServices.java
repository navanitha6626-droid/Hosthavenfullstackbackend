package com.example.hosthaven2.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hosthaven2.entity.MaintenanceTaskEntity;
import com.example.hosthaven2.exception.BusinessValidationException;
import com.example.hosthaven2.exception.ResourceNotFoundException;
import com.example.hosthaven2.repository.MaintenanceTaskRepository;

@Service
public class MaintenanceTaskServices {

    @Autowired
    MaintenanceTaskRepository taskRepo;

    // Create Task
    public MaintenanceTaskEntity saveData(MaintenanceTaskEntity data) {

        if (data.getScheduledDate() == null) {
            throw new BusinessValidationException("Scheduled date cannot be null");
        }

        if (data.getScheduledDate().isBefore(LocalDate.now())) {
            throw new BusinessValidationException("Scheduled date cannot be in the past");
        }

        return taskRepo.save(data);
    }

    // Get All Tasks
    public List<MaintenanceTaskEntity> getAllData() {
        return taskRepo.findAll();
    }

    // Get Task By ID
    public MaintenanceTaskEntity getTaskDetails(Long id) {

        return taskRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
    }

    // Update Task
    public MaintenanceTaskEntity updateTask(Long id, MaintenanceTaskEntity data) {

        MaintenanceTaskEntity viewData = taskRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        if (data.getScheduledDate() == null) {
            throw new BusinessValidationException("Scheduled date cannot be null");
        }

        if (data.getScheduledDate().isBefore(LocalDate.now())) {
            throw new BusinessValidationException("Scheduled date cannot be in the past");
        }

        // Do not update ID
        viewData.setScheduledDate(data.getScheduledDate());

        return taskRepo.save(viewData);
    }

    // Delete Task
    public MaintenanceTaskEntity getDelete(Long id) {

        MaintenanceTaskEntity task = taskRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        taskRepo.delete(task);

        return task;
    }
}
