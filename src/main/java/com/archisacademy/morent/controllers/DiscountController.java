package com.archisacademy.morent.controllers;


import com.archisacademy.morent.dtos.requests.CreateDiscountRequest;
import com.archisacademy.morent.dtos.responses.DiscountResponse;
import com.archisacademy.morent.services.abstracts.DiscountService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/discounts")
@RequiredArgsConstructor
public class DiscountController {

    private final DiscountService discountService;

    @PostMapping
    public ResponseEntity<DiscountResponse> createDiscount(@RequestBody CreateDiscountRequest request) {
        DiscountResponse response = discountService.createDiscount(request);
        return ResponseEntity.ok(response);
    }
}
