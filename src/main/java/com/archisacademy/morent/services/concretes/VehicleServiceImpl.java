package com.archisacademy.morent.services.concretes;

import com.archisacademy.morent.dtos.requests.VehicleRequest;
import com.archisacademy.morent.dtos.responses.VehicleResponse;
import com.archisacademy.morent.entities.Vehicle;
import com.archisacademy.morent.repositories.VehicleRepository;
import com.archisacademy.morent.services.abstracts.VehicleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final ModelMapper modelMapper;

    @Override
    public VehicleResponse addVehicle(VehicleRequest vehicleRequest) {
        Vehicle vehicle = modelMapper.map(vehicleRequest, Vehicle.class);
        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        return new VehicleResponse("Vehicle added successfully", savedVehicle.getVehicleId());
    }

    @Override
    public VehicleResponse getVehicleById(String vehicleId){
        Optional<Vehicle> vehicle = vehicleRepository.findByVehicleId(UUID.fromString(vehicleId));
        if(vehicle.isPresent()){
            vehicle.get();
            return new VehicleResponse(
                    "Vehicle retrieved successfully",
                    vehicle.get().getVehicleId(),
                    vehicle.get().getMake(),
                    vehicle.get().getModel(),
                    vehicle.get().getYear(),
                    vehicle.get().getPricePerDay().doubleValue(),
                    String.join(", ", vehicle.get().getFeatures()),
                    vehicle.get().isAvailability(),
                    vehicle.get().getTermsAndConditions()
            );
        }
        return null;

    }
}
