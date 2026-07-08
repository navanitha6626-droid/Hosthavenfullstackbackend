package com.example.hosthaven2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.hosthaven2.entity.SystemusersEntity;
import com.example.hosthaven2.services.SystemusersServices;

@RestController
@RequestMapping("/syst")
public class SystemusersController {
    @Autowired
    SystemusersServices systemSer;

    @PostMapping("/postData")
   // @PreAuthorize("hasRole('ADMIN')","hasRole('USER')")
    public SystemusersEntity saveData(@RequestBody SystemusersEntity data) {
        return systemSer.saveData(data);
    }

    @GetMapping("/get")

    public List<SystemusersEntity> getData() {
        return systemSer.getAllData();
    }

    @GetMapping("/get/{id}")
    public SystemusersEntity getUserData(@PathVariable Long id) {
        return systemSer.getUsersDetails(id);
    }

    @PutMapping("/gett/{id}")
    public SystemusersEntity updateData(@PathVariable Long id, @RequestBody SystemusersEntity data) {
        return systemSer.updateSystemusers(id, data);
    }

    @DeleteMapping("/get/{id}")
    public SystemusersEntity getDelete(@PathVariable Long id) {
        return systemSer.getDelete(id);

    }
    @PostMapping("/get/gmail")
    public String generateTokens(@RequestParam("mail") String gmail){
        return systemSer.generateToken(gmail);
    }
}
