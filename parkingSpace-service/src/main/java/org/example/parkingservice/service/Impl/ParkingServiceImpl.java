package org.example.parkingservice.service.Impl;

import org.example.parkingservice.dto.ParkingSpaceDTO;
import org.example.parkingservice.entity.ParkingSpace;
import org.example.parkingservice.repo.ParkingSpaceRepo;
import org.example.parkingservice.service.ParkingService;
import org.example.parkingservice.util.VarList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingServiceImpl implements ParkingService {

    private final ParkingSpaceRepo parkingSpaceRepo;
    private final ModelMapper modelMapper;

    public ParkingServiceImpl(ParkingSpaceRepo parkingSpaceRepo, ModelMapper modelMapper) {
        this.parkingSpaceRepo = parkingSpaceRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public int create(ParkingSpaceDTO dto) {
        if (!parkingSpaceRepo.existsById((dto.getSpaceNumber()))){
            parkingSpaceRepo.save(modelMapper.map(dto, ParkingSpace.class));
            return VarList.Created;
        }

        return VarList.Not_Acceptable;
    }

    @Override
    public List<ParkingSpaceDTO> getAll() {
        return parkingSpaceRepo.findAll().stream().map(p -> modelMapper.map(p, ParkingSpaceDTO.class)).toList();
    }

    @Override
    public List<ParkingSpaceDTO> findByLocation(String location) {
        return parkingSpaceRepo.findByLocationContainingIgnoreCase(location).stream().map(p -> modelMapper.map(p, ParkingSpaceDTO.class)).toList();
    }

    @Override
    public ParkingSpaceDTO updateStatus(Long id, String status, String email) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
