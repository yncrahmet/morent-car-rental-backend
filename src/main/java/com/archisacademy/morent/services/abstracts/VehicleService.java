package com.archisacademy.morent.services.abstracts;

import com.archisacademy.morent.dtos.requests.VehicleRequest;
import com.archisacademy.morent.dtos.requests.VehicleUpdateRequest;
import com.archisacademy.morent.dtos.responses.VehicleResponse;

import java.util.UUID;

public interface VehicleService {
    VehicleResponse addVehicle(VehicleRequest vehicleRequest);

    VehicleResponse updateVehicle(UUID vehicleId, VehicleUpdateRequest vehicleUpdateRequest);

}
