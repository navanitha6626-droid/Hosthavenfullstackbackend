package com.example.hosthaven2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.hosthaven2.entity.PropertyListingEntity;
import com.example.hosthaven2.services.PropertyListingServices;

@RestController
@RequestMapping("/Listing")
public class PropertyListingController {
    @Autowired
    PropertyListingServices propertySer;

    @PostMapping("/postProperty")
    public PropertyListingEntity saveData(@RequestBody PropertyListingEntity data) {
        return propertySer.saveData(data);
    }

    @GetMapping("/getProperty")
    public List<PropertyListingEntity> getData() {
        return propertySer.getAllData();
    }

    @GetMapping("/getProperty/{id}")
    public PropertyListingEntity getPropertyData(@PathVariable Long id) {
        return propertySer.getPropertyDetails(id);
    }

    @PutMapping("/getProperty/{id}")
    public PropertyListingEntity updateData(@PathVariable Long id,@RequestBody PropertyListingEntity data) {
        return propertySer.updateProperty(id, data);
    }

@DeleteMapping("/getProperty/{id}")
public PropertyListingEntity getDelete(@PathVariable Long id) {
    return propertySer.getDelete(id);
}

}
