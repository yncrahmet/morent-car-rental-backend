package com.archisacademy.morent.services.abstracts;

import com.archisacademy.morent.dtos.requests.PaymentIntentRequest;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

public interface StripeService {

    PaymentIntent createPaymentIntent(PaymentIntentRequest paymentIntentRequest) throws StripeException;

}
