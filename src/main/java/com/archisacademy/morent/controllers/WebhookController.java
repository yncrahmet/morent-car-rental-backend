package com.archisacademy.morent.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/webhook")
public class WebhookController {

    private static final Logger log = LoggerFactory.getLogger(WebhookController.class);

    @Value("${iyzico.apiKey}")
    private String apiKey;

    @Value("${iyzico.secretKey}")
    private String secretKey;

    @PostMapping("/iyzico")
    public String handleIyzicoWebhook(HttpServletRequest request) {
        try {
            String payload = getRequestBody(request);
            String signature = request.getHeader("x-iyzi-signature");

            if (!isSignatureValid(signature, payload)) {
                log.warn("Invalid signature!");
                return "Signature Mismatch";
            }

            log.info("Iyzico webhook payload: {}", payload);

            if (payload.contains("\"paymentStatus\":\"SUCCESS\"")) {
                log.info("Payment successful!");
            } else {
                log.warn("Payment failed.");
            }

            return "OK";

        } catch (Exception e) {
            log.error("Webhook processing error: ", e);
            return "Error";
        }
    }

    private boolean isSignatureValid(String signature, String payload) {
        try {

            String dataToSign = apiKey + payload + secretKey;
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(secretKeySpec);
            byte[] hash = mac.doFinal(dataToSign.getBytes(StandardCharsets.UTF_8));
            String calculatedSignature = Base64.getEncoder().encodeToString(hash);

            return calculatedSignature.equals(signature);
        } catch (Exception e) {
            log.error("Signature verification error: ", e);
            return false;
        }
    }

    private String getRequestBody(HttpServletRequest request) throws IOException {
        try (BufferedReader reader = request.getReader()) {
            return reader.lines().collect(Collectors.joining(System.lineSeparator()));
        }
    }

}

