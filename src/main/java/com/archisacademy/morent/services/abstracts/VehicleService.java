package com.archisacademy.morent.services.abstracts;

import com.archisacademy.morent.dtos.requests.VehicleRequest;
import com.archisacademy.morent.dtos.responses.VehicleResponse;
import com.archisacademy.morent.entities.Vehicle;

public interface VehicleService {
    VehicleResponse addVehicle(VehicleRequest vehicleRequest);
}
