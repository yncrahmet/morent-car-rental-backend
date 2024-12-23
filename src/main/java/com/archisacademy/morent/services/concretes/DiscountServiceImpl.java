package com.archisacademy.morent.services.concretes;


import com.archisacademy.morent.dtos.requests.ApplyDiscountRequest;
import com.archisacademy.morent.dtos.requests.CreateDiscountRequest;
import com.archisacademy.morent.dtos.responses.ApplyDiscountResponse;
import com.archisacademy.morent.dtos.responses.DiscountResponse;
import com.archisacademy.morent.entities.Discount;
import com.archisacademy.morent.repositories.DiscountRepository;
import com.archisacademy.morent.services.abstracts.DiscountService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

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

    @Override
    public ApplyDiscountResponse applyDiscount(ApplyDiscountRequest request) {
        // Veritabanından Discount kodunu kontrol et
        Optional<Discount> discountOpt = discountRepository.findByCode(request.getCode());
        if (discountOpt.isEmpty()) {
            throw new RuntimeException("Invalid discount code");
        }

        Discount discount = discountOpt.get();

        // Kodun geçerlilik süresini kontrol et
        if (discount.getExpiryDate().isBefore(java.time.LocalDate.now())) {
            throw new RuntimeException("Discount code has expired");
        }

        // Booking için mock edilmiş bir toplam tutar (örnek olarak 100.00 kullanıyoruz)
        BigDecimal bookingAmount = new BigDecimal("100.00");

        // Yeni toplam tutarı hesapla
        BigDecimal discountAmount = bookingAmount.multiply(BigDecimal.valueOf(discount.getDiscountPercentage()).divide(BigDecimal.valueOf(100)));
        BigDecimal newTotalAmount = bookingAmount.subtract(discountAmount);

        // Yanıt döndür
        return new ApplyDiscountResponse("Discount applied successfully", newTotalAmount);
    }
}
