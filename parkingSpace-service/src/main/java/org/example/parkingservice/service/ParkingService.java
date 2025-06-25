package org.example.parkingservice.service;

import org.example.parkingservice.dto.ParkingSpaceDTO;

import java.util.List;

public interface ParkingService {
    int create(ParkingSpaceDTO dto);
    List<ParkingSpaceDTO> getAll();
    List<ParkingSpaceDTO> findByLocation(String location);
    ParkingSpaceDTO updateStatus(Long id, String status, String reservedBy);
    boolean delete(Long id);
}
