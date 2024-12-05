package com.archisacademy.morent.services.abstracts;

import com.archisacademy.morent.dtos.requests.VehicleRequest;
import com.archisacademy.morent.entities.Vehicle;

public interface VehicleService {
    Vehicle saveVehicle(Vehicle vehicle);
}
