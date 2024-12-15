package com.archisacademy.morent.services.concretes;

import com.archisacademy.morent.dtos.requests.ReviewRequest;
import com.archisacademy.morent.dtos.responses.ReviewResponse;
import com.archisacademy.morent.entities.Review;
import com.archisacademy.morent.entities.Vehicle;
import com.archisacademy.morent.exceptions.VehicleNotFoundException;
import com.archisacademy.morent.repositories.ReviewRepository;
import com.archisacademy.morent.repositories.VehicleRepository;
import com.archisacademy.morent.services.abstracts.ReviewService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

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
        return new ReviewResponse("Vehicle has been added succesfully");
    }

}
