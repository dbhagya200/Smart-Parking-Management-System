package org.example.parkingservice.controller;

import org.example.parkingservice.dto.ParkingSpaceDTO;
import org.example.parkingservice.service.ParkingService;
import org.example.parkingservice.util.VarList;
import org.example.userservice.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parkingSpace")
public class ParkingSpaceController {

private final ParkingService parkingService;

    public ParkingSpaceController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    ResponseEntity<ResponseDTO> createParkingSpace(@RequestHeader("Authorization") String authorization, @RequestBody ParkingSpaceDTO parkingSpace) {
        int status = parkingService.create(parkingSpace);
        switch (status) {
            case VarList.Created -> {
                return ResponseEntity.status(VarList.Created).body(
                        new ResponseDTO(VarList.Created, "Parking space created successfully", parkingSpace)
                );
            }
            case VarList.Not_Acceptable -> {
                return ResponseEntity.status(VarList.Not_Acceptable).body(
                        new ResponseDTO(VarList.Not_Acceptable, "Parking space already exists", null)
                );
            }
            default -> {
                return ResponseEntity.status(VarList.Internal_Server_Error).body(
                        new ResponseDTO(VarList.Internal_Server_Error, "Failed to create parking space", null)
                );
            }
        }
    }

    @GetMapping
    public ResponseEntity<List<ParkingSpaceDTO>> getAll() {
        return ResponseEntity.ok(parkingService.getAll());
    }

    @GetMapping("/search")
    public ResponseEntity<List<ParkingSpaceDTO>> byLocation(@RequestParam String location) {
        return ResponseEntity.ok(parkingService.findByLocation(location));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ParkingSpaceDTO> updateStatus(
            @PathVariable Long id,
            @RequestParam String status,
            @RequestParam(required = false) String reservedBy) {
        return ResponseEntity.ok(parkingService.updateStatus(id, status, reservedBy));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return parkingService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
