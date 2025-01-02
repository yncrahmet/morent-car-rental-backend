package com.archisacademy.morent.repositories;

import com.archisacademy.morent.dtos.responses.PaymentResponse;
import com.archisacademy.morent.entities.Booking;
import com.archisacademy.morent.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PaymentRepository extends JpaRepository<Payment, Long> {
    PaymentResponse findByBookingId(Booking bookingId);
}
