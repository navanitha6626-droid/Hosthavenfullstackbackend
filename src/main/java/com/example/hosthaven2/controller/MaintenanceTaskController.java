package com.example.hosthaven2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.hosthaven2.entity.MaintenanceTaskEntity;
import com.example.hosthaven2.services.MaintenanceTaskServices;

@RestController
@RequestMapping("/Task")
public class MaintenanceTaskController {
    @Autowired
    MaintenanceTaskServices taskSer;

    @PostMapping("/postTask")
    public MaintenanceTaskEntity saveData(@RequestBody MaintenanceTaskEntity data) {
        return taskSer.saveData(data);
    }

    @GetMapping("/getTask")
    public List<MaintenanceTaskEntity> getData() {
        return taskSer.getAllData();
    }

    @GetMapping("/getTask/{id}")
    public MaintenanceTaskEntity getTaskData(@PathVariable Long id) {
        return taskSer.getTaskDetails(id);
    }

    @PutMapping("/getTask/{id}")
    public MaintenanceTaskEntity updateData(@PathVariable Long id,@RequestBody MaintenanceTaskEntity data) {
        return taskSer.updateTask(id, data);
    }

    @DeleteMapping("/getTask/{id}")
    public MaintenanceTaskEntity getDelete(@PathVariable Long id) {
       return taskSer.getDelete(id);
    }

}
