package org.example.parkingservice.feign;
import org.example.userservice.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "parking-service")
public interface ClientParkingSpace {

    @GetMapping("user-service/api/v1/user/check")
    ResponseEntity<Boolean> isExistByEmail(@RequestParam("email") String email);

    @GetMapping("user-service/api/v1/user/get/{email}")
    ResponseEntity<User> getUserByEmail(@PathVariable("email") String email);

}
