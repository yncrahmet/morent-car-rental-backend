package com.archisacademy.morent.services.abstracts;

import com.archisacademy.morent.dtos.requests.ReviewRequest;
import com.archisacademy.morent.dtos.responses.ReviewResponse;
import com.archisacademy.morent.dtos.responses.VehicleResponse;

import java.util.UUID;

public interface ReviewService {

    ReviewResponse submitReview(ReviewRequest reviewRequest);
}
