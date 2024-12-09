package com.archisacademy.morent.controllers;

import com.archisacademy.morent.dtos.requests.VehicleRequest;
import com.archisacademy.morent.dtos.requests.VehicleUpdateRequest;
import com.archisacademy.morent.dtos.responses.VehicleResponse;
import com.archisacademy.morent.dtos.responses.VehicleUpdateResponse;
import com.archisacademy.morent.services.abstracts.VehicleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<VehicleResponse> addVehicle(@Valid @RequestBody VehicleRequest vehicleRequest) {
        VehicleResponse response = vehicleService.addVehicle(vehicleRequest);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{vehicleId}")
    public ResponseEntity<VehicleUpdateResponse> updateVehicle(@PathVariable UUID vehicleId,
                                                               @Valid @RequestBody VehicleUpdateRequest vehicleUpdateRequest) {
        VehicleUpdateResponse response = vehicleService.updateVehicle(vehicleId, vehicleUpdateRequest);
        return ResponseEntity.ok(response);
    }

}
