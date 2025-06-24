package org.example.vehicleservice.service;

import org.example.vehicleservice.dto.VehicleDTO;
import org.example.vehicleservice.entity.Vehicle;

import java.util.List;

public interface VehicleService {
    VehicleDTO saveVehicle(VehicleDTO dto);
    List<VehicleDTO> getAllVehicles();
    VehicleDTO getVehicleByPlate(String plateNumber);
    boolean deleteVehicle(Long id);
}
