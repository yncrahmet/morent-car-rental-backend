package com.archisacademy.morent.services.abstracts;

import com.archisacademy.morent.dtos.requests.IyzicoPaymentRequest;
import com.iyzipay.model.Payment;

public interface IyzicoService {

    Payment processPayment(IyzicoPaymentRequest request);

}
