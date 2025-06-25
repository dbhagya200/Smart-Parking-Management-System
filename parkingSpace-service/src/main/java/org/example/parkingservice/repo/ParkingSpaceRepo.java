package org.example.parkingservice.repo;

import org.example.parkingservice.entity.ParkingSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingSpaceRepo extends JpaRepository<ParkingSpace,String> {
    List<ParkingSpace> findByLocationContainingIgnoreCase(String location);
    List<ParkingSpace> findByStatus(String status);

}
