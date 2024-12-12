package com.archisacademy.morent.services.concretes;

import com.archisacademy.morent.dtos.requests.VehicleRequest;
import com.archisacademy.morent.dtos.responses.VehicleDetails;
import com.archisacademy.morent.dtos.responses.VehicleResponse;
import com.archisacademy.morent.entities.Vehicle;
import com.archisacademy.morent.exceptions.VehicleNotFoundException;
import com.archisacademy.morent.repositories.VehicleRepository;
import com.archisacademy.morent.services.abstracts.VehicleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
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
    public VehicleDetails getVehicleById(UUID vehicleId){
        Optional<Vehicle> vehicle = vehicleRepository.findByVehicleId(vehicleId);
        return vehicle.map(vehicle1 -> {
            VehicleDetails vehicleDetails = new VehicleDetails();
            BeanUtils.copyProperties(vehicle1, vehicleDetails);
            return vehicleDetails;
        }).orElseThrow(() -> new VehicleNotFoundException("Vehicle id is wrong."));
    }

}
