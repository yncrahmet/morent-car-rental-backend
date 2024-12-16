package com.archisacademy.morent.services.concretes;


import com.archisacademy.morent.dtos.requests.CreateDiscountRequest;
import com.archisacademy.morent.dtos.responses.DiscountResponse;
import com.archisacademy.morent.entities.Discount;
import com.archisacademy.morent.repositories.DiscountRepository;
import com.archisacademy.morent.services.abstracts.DiscountService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {

    private final DiscountRepository discountRepository;

    @Override
    public DiscountResponse createDiscount(CreateDiscountRequest request) {
        if (discountRepository.existsByCode(request.getCode())) {
            throw new RuntimeException("Discount code already exists!");
        }

        Discount discount = new Discount();
        discount.setCode(request.getCode());
        discount.setDiscountPercentage(request.getDiscountPercentage());
        discount.setExpiryDate(LocalDate.parse(request.getExpiryDate()));

        Discount savedDiscount = discountRepository.save(discount);

        return new DiscountResponse("Discount code created successfully", savedDiscount.getId());
    }
}
