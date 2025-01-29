package com.archisacademy.morent.dtos.requests;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class IyzicoPaymentRequest {

    private String cardHolderName;

    private String cardNumber;

    private String expireMonth;

    private String expireYear;

    private String cvc;

    private BigDecimal price;

    private String currency;

    private UUID userId;

}
