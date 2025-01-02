package com.archisacademy.morent.services.abstracts;

import com.archisacademy.morent.dtos.requests.PaymentRequest;
import com.archisacademy.morent.dtos.responses.PaymentResponse;

public interface PaymentService {
    PaymentResponse initiatePayment(PaymentRequest paymentRequest);
}
