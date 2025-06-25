package org.example.parkingservice.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class ParkingSpaceDTO {

    private String spaceNumber;
    private String location;
    private boolean available;
    private String reservedByEmail;
}
