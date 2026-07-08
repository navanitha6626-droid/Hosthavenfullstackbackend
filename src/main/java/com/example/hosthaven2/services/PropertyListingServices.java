package com.example.hosthaven2.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hosthaven2.entity.PropertyListingEntity;
import com.example.hosthaven2.exception.BusinessValidationException;
import com.example.hosthaven2.exception.ResourceNotFoundException;
import com.example.hosthaven2.repository.PropertyListingRepository;

@Service
public class PropertyListingServices {

    @Autowired
    PropertyListingRepository propertyRepo;

    // Create Property
    public PropertyListingEntity saveData(PropertyListingEntity data) {

        // Title Validation
        if (data.getTitle() == null || data.getTitle().trim().isEmpty()) {
            throw new BusinessValidationException("Property title cannot be empty");
        }

        // Base Price Validation
        if (data.getBasePrice() == null) {
            throw new BusinessValidationException("Base price is required");
        }

        if (data.getBasePrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessValidationException("Base price must be greater than zero");
        }

        // Average Rating Validation
        if (data.getAverageRating() == null) {
            throw new BusinessValidationException("Average rating is required");
        }

        if (data.getAverageRating().compareTo(BigDecimal.ZERO) < 0
                || data.getAverageRating().compareTo(new BigDecimal("5")) > 0) {

            throw new BusinessValidationException("Average rating must be between 0 and 5");
        }

        return propertyRepo.save(data);
    }

    // Get All Properties
    public List<PropertyListingEntity> getAllData() {
        return propertyRepo.findAll();
    }

    // Get Property By ID
    public PropertyListingEntity getPropertyDetails(Long id) {

        return propertyRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found"));
    }

    // Update Property
    public PropertyListingEntity updateProperty(Long id, PropertyListingEntity data) {

        PropertyListingEntity viewData = propertyRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found"));

        // Title Validation
        if (data.getTitle() == null || data.getTitle().trim().isEmpty()) {
            throw new BusinessValidationException("Property title cannot be empty");
        }

        // Base Price Validation
        if (data.getBasePrice() == null) {
            throw new BusinessValidationException("Base price is required");
        }

        if (data.getBasePrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessValidationException("Base price must be greater than zero");
        }

        // Average Rating Validation
        if (data.getAverageRating() == null) {
            throw new BusinessValidationException("Average rating is required");
        }

        if (data.getAverageRating().compareTo(BigDecimal.ZERO) < 0
                || data.getAverageRating().compareTo(new BigDecimal("5")) > 0) {

            throw new BusinessValidationException("Average rating must be between 0 and 5");
        }

        // Update fields (Do not update ID)
        viewData.setTitle(data.getTitle());
        viewData.setDescription(data.getDescription());
        viewData.setAddress(data.getAddress());
        viewData.setBasePrice(data.getBasePrice());
        viewData.setAverageRating(data.getAverageRating());
        viewData.setBenefits(data.getBenefits());

        return propertyRepo.save(viewData);
    }

    // Delete Property
    public PropertyListingEntity getDelete(Long id) {

        PropertyListingEntity property = propertyRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found"));

        propertyRepo.delete(property);

        return property;
    }
}
