package com.archisacademy.morent.controllers;


import com.archisacademy.morent.dtos.requests.ReviewRequest;
import com.archisacademy.morent.dtos.responses.ReviewResponse;
import com.archisacademy.morent.services.abstracts.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;


    @PostMapping
    public ResponseEntity<ReviewResponse> submitReview(@RequestBody ReviewRequest reviewRequest) {
        ReviewResponse response = reviewService.submitReview(reviewRequest);
        return ResponseEntity.ok(response);
    }
}
