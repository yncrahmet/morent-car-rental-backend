package com.archisacademy.morent.services.abstracts;

import com.archisacademy.morent.dtos.requests.PaymentRefundRequest;
import com.archisacademy.morent.dtos.requests.PaymentRequest;
import com.archisacademy.morent.dtos.responses.PaymentRefundResponse;
import com.archisacademy.morent.dtos.responses.PaymentResponse;

public interface PaymentService {
    PaymentResponse initiatePayment(PaymentRequest paymentRequest);

    PaymentRefundResponse refundPayment(PaymentRefundRequest paymentRefundRequest);

}
