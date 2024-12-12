package com.archisacademy.morent.services.abstracts;

import com.archisacademy.morent.dtos.requests.VehicleRequest;
import com.archisacademy.morent.dtos.responses.SearchVehicleResponse;
import com.archisacademy.morent.dtos.responses.VehicleResponse;
import com.archisacademy.morent.entities.Vehicle;

import java.time.LocalDate;
import java.util.List;

public interface VehicleService {
    VehicleResponse addVehicle(VehicleRequest vehicleRequest);
    List<SearchVehicleResponse> searchVehicles(String location, LocalDate startDate, LocalDate endDate, String vehicleType);
}
