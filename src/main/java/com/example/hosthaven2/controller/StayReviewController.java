package com.example.hosthaven2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.hosthaven2.entity.StayReviewEntity;
import com.example.hosthaven2.services.StayReviewServices;

@RestController
@RequestMapping("/Review")
public class StayReviewController {
    @Autowired
    StayReviewServices reviewSer;

    @PostMapping("/postReview")
    public StayReviewEntity saveData(@RequestBody StayReviewEntity data) {
        return reviewSer.saveData(data);
    }

    @GetMapping("/getReview")
    public List<StayReviewEntity> getData() {
        return reviewSer.getAllData();
    }

    @GetMapping("/getReview/{id}")
    public StayReviewEntity getReviewData(@PathVariable Long id) {
        return reviewSer.getReviewDetails(id);
    }

    @PutMapping("/getReview/{id}")
    public StayReviewEntity updateData(@PathVariable Long id,@RequestBody StayReviewEntity data) {
        return reviewSer.updateReview(id, data);
    }

    @DeleteMapping("/getReview/{id}")
    public StayReviewEntity getDelete(@PathVariable Long id) {
        return reviewSer.getDelete(id);
    }

}
