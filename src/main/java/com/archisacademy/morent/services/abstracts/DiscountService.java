package com.archisacademy.morent.services.abstracts;


import com.archisacademy.morent.dtos.requests.ApplyDiscountRequest;
import com.archisacademy.morent.dtos.requests.CreateDiscountRequest;
import com.archisacademy.morent.dtos.responses.ApplyDiscountResponse;
import com.archisacademy.morent.dtos.responses.DiscountResponse;

public interface DiscountService {
    DiscountResponse createDiscount(CreateDiscountRequest request);
    ApplyDiscountResponse applyDiscount(ApplyDiscountRequest request);
}
