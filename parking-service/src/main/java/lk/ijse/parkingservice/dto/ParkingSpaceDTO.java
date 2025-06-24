package lk.ijse.parkingservice.dto;

import lombok.Data;

@Data
public class ParkingSpaceDTO {
    private Long id;
    private String location;
    private String slotNumber;
    private boolean available;
    private String vehicleType;
    private String status;
}
