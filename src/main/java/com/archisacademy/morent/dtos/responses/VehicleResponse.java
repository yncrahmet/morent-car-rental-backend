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

}
