package com.archisacademy.morent.controllers;

import com.archisacademy.morent.dtos.requests.VehicleRequest;
import com.archisacademy.morent.dtos.responses.VehicleResponse;
import com.archisacademy.morent.entities.Vehicle;
import com.archisacademy.morent.services.abstracts.VehicleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<VehicleResponse> addVehicle(@Valid  @RequestBody VehicleRequest vehicleRequest) {
        Vehicle vehicle = modelMapper.map(vehicleRequest, Vehicle.class);
        Vehicle savedVehicle = vehicleService.saveVehicle(vehicle);
        VehicleResponse response = new VehicleResponse("Vehicle added successfully", savedVehicle.getVehicleId());
        return ResponseEntity.ok(response);
    }
}
