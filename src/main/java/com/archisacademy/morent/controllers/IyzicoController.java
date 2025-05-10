package com.archisacademy.morent.controllers;

import com.archisacademy.morent.dtos.requests.IyzicoPaymentRequest;
import com.archisacademy.morent.services.abstracts.IyzicoService;
import com.iyzipay.model.Payment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/iyzico")
public class IyzicoController {

    private final IyzicoService iyzicoService;

    public IyzicoController(IyzicoService iyzicoService) {
        this.iyzicoService = iyzicoService;
    }

    @PostMapping
    public ResponseEntity<Payment> processPayment(@RequestBody IyzicoPaymentRequest request) {
        Payment payment = iyzicoService.processPayment(request);
        return ResponseEntity.ok(payment);
    }

}
