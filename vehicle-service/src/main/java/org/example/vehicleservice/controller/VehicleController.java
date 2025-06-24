package org.example.vehicleservice.controller;




import org.example.vehicleservice.dto.VehicleDTO;
import org.example.vehicleservice.feign.ClientVehicle;
import org.example.vehicleservice.repo.VehicleRepo;
import org.example.vehicleservice.service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicle")
public class VehicleController {
    private final VehicleService vehicleService;
    private final ClientVehicle clientVehicle;

    public VehicleController(VehicleRepo vehicleRepo, VehicleService vehicleService, ClientVehicle clientVehicle) {
        this.vehicleService = vehicleService;
        this.clientVehicle = clientVehicle;

    }

    @PostMapping("/create")
    public ResponseEntity<VehicleDTO> create(@RequestBody VehicleDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(vehicleService.saveVehicle(dto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<VehicleDTO>> getAll() {
        return ResponseEntity.ok(vehicleService.getAllVehicles());
    }

    @GetMapping("/{plate}")
    public ResponseEntity<VehicleDTO> getByPlate(@PathVariable String plate) {
        return ResponseEntity.ok(vehicleService.getVehicleByPlate(plate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return vehicleService.deleteVehicle(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
    // Register new vehicle with user email
/*    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> registerVehicle(@RequestBody VehicleDTO vehicleDto) {
        int response = vehicleService.saveVehicle(vehicleDto);

        switch (response){
            case VarList.Created -> {
                return ResponseEntity.status(201)
                        .body(new ResponseDTO(VarList.Created, "User created successfully",vehicleDto ));
            }
            case VarList.Not_Acceptable -> {
                return ResponseEntity.status(406)
                        .body(new ResponseDTO(VarList.Not_Acceptable, "Email already used", null));
            }
            case VarList.Bad_Request -> {
                return ResponseEntity.status(400)
                        .body(new ResponseDTO(VarList.Bad_Request, "Invalid data provided", null));
            }
            default -> {
                return ResponseEntity.status(500)
                        .body(new ResponseDTO(VarList.Internal_Server_Error, "An error occurred", null));
            }
        }
    }*/

   /* @PostMapping("/register")
    public ResponseEntity<ResponseDTO> registerVehicle(@RequestBody VehicleDTO vehicleDto) {
        int response = vehicleService.saveVehicle(vehicleDto);

        return switch (response) {
            case VarList.Created -> ResponseEntity.status(201)
                    .body(new ResponseDTO(VarList.Created, "Vehicle saved successfully", vehicleDto));
            case VarList.Not_Acceptable -> ResponseEntity.status(406)
                    .body(new ResponseDTO(VarList.Not_Acceptable, "Email not found in User Service", null));
            case VarList.Bad_Request -> ResponseEntity.status(400)
                    .body(new ResponseDTO(VarList.Bad_Request, "Invalid data provided", null));
            default -> ResponseEntity.status(500)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, "Server error", null));
        };
    }*/

}
