package com.example.hosthaven2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hosthaven2.entity.StayReviewEntity;
import com.example.hosthaven2.exception.BusinessValidationException;
import com.example.hosthaven2.exception.ResourceNotFoundException;
import com.example.hosthaven2.repository.StayReviewRepository;

@Service
public class StayReviewServices {

    @Autowired
    StayReviewRepository reviewRepo;

    // Create Review
    public StayReviewEntity saveData(StayReviewEntity data) {

        if (data.getRating() < 1 || data.getRating() > 5) {
            throw new BusinessValidationException("Rating must be between 1 and 5");
        }

        if (data.getComment() == null || data.getComment().trim().isEmpty()) {
            throw new BusinessValidationException("Comment cannot be empty");
        }

        return reviewRepo.save(data);
    }

    // Get All Reviews
    public List<StayReviewEntity> getAllData() {
        return reviewRepo.findAll();
    }

    // Get Review By ID
    public StayReviewEntity getReviewDetails(Long id) {

        return reviewRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found"));
    }

    // Update Review
    public StayReviewEntity updateReview(Long id, StayReviewEntity data) {

        StayReviewEntity viewData = reviewRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found"));

        if (data.getRating() < 1 || data.getRating() > 5) {
            throw new BusinessValidationException("Rating must be between 1 and 5");
        }

        if (data.getComment() == null || data.getComment().trim().isEmpty()) {
            throw new BusinessValidationException("Comment cannot be empty");
        }

        // Don't update ID
        viewData.setRating(data.getRating());
        viewData.setComment(data.getComment());
        viewData.setCreatedAt(data.getCreatedAt());

        return reviewRepo.save(viewData);
    }

    // Delete Review
    public StayReviewEntity getDelete(Long id) {

        StayReviewEntity review = reviewRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found"));

        reviewRepo.delete(review);

        return review;
    }
}
