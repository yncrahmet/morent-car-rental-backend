package com.archisacademy.morent.dtos.requests;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentIntentRequest {

    @NotNull(message = "Amount cannot be null")
    private Long amount;

    @NotNull(message = "Currency cannot be null")
    private String currency;

}
