package com.archisacademy.morent.controllers;

import com.archisacademy.morent.dtos.requests.VehicleRequest;
import com.archisacademy.morent.dtos.responses.SearchVehicleResponse;
import com.archisacademy.morent.dtos.responses.VehicleResponse;
import com.archisacademy.morent.services.abstracts.VehicleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

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


    @GetMapping("/search")
    public ResponseEntity<List<SearchVehicleResponse>> searchVehicles(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate,
            @RequestParam(required = false) String vehicleType) {
        List<SearchVehicleResponse> response = vehicleService.searchVehicles(location, startDate, endDate, vehicleType);
        return ResponseEntity.ok(response);
    }
}
