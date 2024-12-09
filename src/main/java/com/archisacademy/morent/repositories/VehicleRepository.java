package com.archisacademy.morent.repositories;

import com.archisacademy.morent.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VehicleRepository extends JpaRepository<Vehicle,Long> {
    Optional<Vehicle> findByVehicleId(UUID vehicleId);

}
