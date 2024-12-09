package com.archisacademy.morent.services.concretes;

import com.archisacademy.morent.dtos.requests.VehicleRequest;
import com.archisacademy.morent.dtos.responses.VehicleFilterResponse;
import com.archisacademy.morent.dtos.requests.VehicleUpdateRequest;
import com.archisacademy.morent.dtos.responses.VehicleResponse;
import com.archisacademy.morent.dtos.responses.VehicleUpdateResponse;
import com.archisacademy.morent.entities.Vehicle;
import com.archisacademy.morent.exceptions.VehicleNotFoundException;
import com.archisacademy.morent.repositories.VehicleRepository;
import com.archisacademy.morent.services.abstracts.VehicleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<VehicleFilterResponse> getVehiclesByType(String type) {
        List<Vehicle> vehicles = vehicleRepository.findByTypeIgnoreCase(type);
        return vehicles.stream().
                map(vehicle -> modelMapper.map(vehicle, VehicleFilterResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public VehicleUpdateResponse updateVehicle(UUID vehicleId, VehicleUpdateRequest vehicleUpdateRequest) {

        Vehicle vehicle = vehicleRepository.findByVehicleId(vehicleId)
                .orElseThrow(() -> new VehicleNotFoundException("No vehicle to update found!"));

        modelMapper.map(vehicleUpdateRequest, vehicle);

        vehicleRepository.save(vehicle);

        return new VehicleUpdateResponse("Vehicle updated successfully");
    }

}
