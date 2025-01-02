package com.archisacademy.morent.dtos.requests;

import com.archisacademy.morent.entities.Booking;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class PaymentRequest {
    @NotNull(message = "Amount cannot be null")
    private Long amount;
    @NotNull(message = "Payment method cannot be null")
    private String paymentMethod;
    @NotNull(message = "BookingId cannot be null")
    private Booking bookingId;
}
