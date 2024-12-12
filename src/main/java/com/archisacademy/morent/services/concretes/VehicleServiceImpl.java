package com.archisacademy.morent.services.concretes;

import com.archisacademy.morent.dtos.requests.VehicleRequest;
import com.archisacademy.morent.dtos.responses.SearchVehicleResponse;
import com.archisacademy.morent.dtos.responses.VehicleResponse;
import com.archisacademy.morent.entities.Vehicle;
import com.archisacademy.morent.repositories.VehicleRepository;
import com.archisacademy.morent.services.abstracts.VehicleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<SearchVehicleResponse> searchVehicles(String location, LocalDate startDate, LocalDate endDate, String vehicleType) {
        List<Vehicle> vehicles = vehicleRepository.findAll()
                .stream()
                .filter(vehicle -> location == null || vehicle.getLocation().equalsIgnoreCase(location))
                .filter(Vehicle::isAvailability)
                .collect(Collectors.toList());

        if (vehicles.isEmpty()) {
            return List.of();
        }

        return vehicles.stream()
                .map(vehicle -> new SearchVehicleResponse(
                        vehicle.getVehicleId(),
                        vehicle.getMake(),
                        vehicle.getModel(),
                        vehicle.getYear(),
                        vehicle.getLocation(),
                        vehicle.getPricePerDay(),
                        vehicle.getFeatures(),
                        vehicle.isAvailability()
                ))
                .collect(Collectors.toList());
    }

}
