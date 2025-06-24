package org.example.vehicleservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleDTO {
    private Long id;
    private String plateNumber;
    private String email;
    private String username;
    private String color;
    private String model;
}
