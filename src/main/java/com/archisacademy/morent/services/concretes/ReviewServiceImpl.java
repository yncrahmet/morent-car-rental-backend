package com.archisacademy.morent.services.concretes;

import com.archisacademy.morent.dtos.requests.ReviewRequest;
import com.archisacademy.morent.dtos.requests.ReviewUpdateRequest;
import com.archisacademy.morent.dtos.responses.ReviewResponse;
import com.archisacademy.morent.entities.Review;
import com.archisacademy.morent.entities.Vehicle;
import com.archisacademy.morent.repositories.ReviewRepository;
import com.archisacademy.morent.repositories.VehicleRepository;
import com.archisacademy.morent.services.abstracts.ReviewService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ModelMapper modelMapper;
    private final VehicleRepository vehicleRepository;

    @Override
    public ReviewResponse submitReview(ReviewRequest reviewRequest){
        Vehicle vehicle = vehicleRepository.findByVehicleId(reviewRequest.getVehicleId()).orElseThrow(() -> new RuntimeException("Vehicle not found!!!"));
        Review review = modelMapper.map(reviewRequest, Review.class);
        review.setVehicle(vehicle);
        reviewRepository.save(review);
        return new ReviewResponse("Review submitted successfully");
    }

    @Override
    public ReviewResponse updateReview(Long reviewId, ReviewUpdateRequest reviewUpdateRequest) {

        return reviewRepository.findById(reviewId)
                .map(review -> {
                    modelMapper.map(reviewUpdateRequest, review);
                    reviewRepository.save(review);
                    return new ReviewResponse("Review updated successfully");
                })
                .orElse(new ReviewResponse("Review not found"));

    }
    @Override
    public ReviewResponse deleteReview(Long reviewId) {
        Optional<Review> review = reviewRepository.findById(reviewId);
        if (review.isPresent()) {
            reviewRepository.delete(review.get());
            return new ReviewResponse("Review deleted successfully");
        } else {
            return new ReviewResponse("Review not found");
        }
    }
}
