package com.archisacademy.morent.services.concretes;

import com.archisacademy.morent.dtos.requests.PaymentRequest;
import com.archisacademy.morent.dtos.responses.PaymentResponse;
import com.archisacademy.morent.entities.Payment;
import com.archisacademy.morent.repositories.PaymentRepository;
import com.archisacademy.morent.services.abstracts.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public PaymentResponse initiatePayment(PaymentRequest paymentRequest) {
        Payment payment = new Payment();
        payment.setBooking(paymentRequest.getBookingId());
        Payment savedPayment = paymentRepository.save(payment);
        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setPaymentId(savedPayment.getPaymentId());
        paymentResponse.setMessage("Payment initiated.");
        return paymentResponse;
    }
}
