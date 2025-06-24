package org.example.vehicleservice.repo;

import org.example.vehicleservice.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VehicleRepo extends JpaRepository<Vehicle, Long> {
    Vehicle findVehicleByPlateNumber(String plateNumber);
    Vehicle findByPlateNumber(String plateNumber);
}
