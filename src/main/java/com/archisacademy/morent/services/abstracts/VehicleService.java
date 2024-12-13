package com.archisacademy.morent.services.abstracts;

import com.archisacademy.morent.dtos.requests.VehicleRequest;
import com.archisacademy.morent.dtos.responses.VehicleFilterResponse;
import com.archisacademy.morent.dtos.responses.SearchVehicleResponse;
import com.archisacademy.morent.dtos.requests.VehicleUpdateRequest;
import com.archisacademy.morent.dtos.responses.VehicleDetails;
import com.archisacademy.morent.dtos.responses.VehicleResponse;
import java.util.List;
import com.archisacademy.morent.dtos.responses.VehicleUpdateResponse;
import java.util.UUID;
import java.time.LocalDate;


public interface VehicleService {
    VehicleResponse addVehicle(VehicleRequest vehicleRequest);

    VehicleUpdateResponse updateVehicle(UUID vehicleId, VehicleUpdateRequest vehicleUpdateRequest);

    List<VehicleFilterResponse> getVehiclesByType(String type);

    VehicleDetails getVehicleById(UUID vehicleId);

    List<SearchVehicleResponse> searchVehicles(String location, LocalDate startDate, LocalDate endDate, String vehicleType);
}
