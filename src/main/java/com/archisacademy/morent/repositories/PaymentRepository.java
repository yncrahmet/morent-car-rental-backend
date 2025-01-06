package com.archisacademy.morent.repositories;


import com.archisacademy.morent.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
