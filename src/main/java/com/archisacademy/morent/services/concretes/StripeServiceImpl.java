package com.archisacademy.morent.services.concretes;

import com.archisacademy.morent.dtos.requests.NotificationRequest;
import com.archisacademy.morent.dtos.requests.PaymentIntentRequest;
import com.archisacademy.morent.entities.Notification;
import com.archisacademy.morent.services.abstracts.NotificationService;
import com.archisacademy.morent.services.abstracts.StripeService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StripeServiceImpl implements StripeService {

    private final NotificationService notificationService;

    @Value("${stripe.api.key}")
    private String stripeApiKey;

    @Override
    public PaymentIntent createPaymentIntent(PaymentIntentRequest paymentIntentRequest) throws StripeException {
        Stripe.apiKey = stripeApiKey;

        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(paymentIntentRequest.getAmount())
                .setCurrency(paymentIntentRequest.getCurrency())
                .build();

        String message = "Payment intent created successfully";
        notificationService.sendMessage(message);

        return PaymentIntent.create(params);
    }

}
