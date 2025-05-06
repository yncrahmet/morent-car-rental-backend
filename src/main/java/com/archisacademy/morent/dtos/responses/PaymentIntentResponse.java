package com.archisacademy.morent.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentIntentResponse {

    private String paymentIntentId;

    private String clientSecret;

}
