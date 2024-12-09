package com.archisacademy.morent.dtos.responses;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleResponse {

    private String message;
    private UUID vehicleId;
    private String make;
    private String model;
    private int year;
    private double pricePerDay;
    private String features;
    private boolean availability;
    private String termsAndConditions;

}
