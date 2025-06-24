package org.example.userservice.controller;

import org.example.userservice.dto.AuthDTO;
import org.example.userservice.dto.ResponseDTO;
import org.example.userservice.dto.UserDTO;
import org.example.userservice.service.UserService;
import org.example.userservice.util.JwtUtil;
import org.example.userservice.util.VarList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authorization.method.AuthorizeReturnObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public UserController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/getAll")
    public ResponseEntity<ResponseDTO> getAllUsers(@RequestHeader("Authorization") String token) {
        jwtUtil.getUsernameFromToken(token.substring(7));
        List<UserDTO> userList = userService.getAllUsers();
        if (userList.isEmpty()) {
            return ResponseEntity.status(204)
                    .body(new ResponseDTO(VarList.No_Content, "No users found", null));
        } else {
            return ResponseEntity.status(200)
                    .body(new ResponseDTO(VarList.OK, "Users retrieved successfully", userList));
        }
    }

    @GetMapping("/get/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
        try {
            UserDTO userDTO = userService.getUserByEmail(email);
            return ResponseEntity.ok(userDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> register(@RequestBody UserDTO userDTO) {

        try {
            int res = userService.saveUser(userDTO);
            switch (res) {
                case VarList.Created -> {
                    String token = jwtUtil.generateToken(userDTO);
                    AuthDTO authDTO = new AuthDTO();
                    authDTO.setEmail(userDTO.getEmail());
                    authDTO.setToken(token);
                    return ResponseEntity.status(HttpStatus.CREATED)
                            .body(new ResponseDTO(VarList.Created, "Success", authDTO));
                }
                case VarList.Not_Acceptable -> {
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                            .body(new ResponseDTO(VarList.Not_Acceptable, "Email Already Used", null));
                }
                default -> {
                    return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                            .body(new ResponseDTO(VarList.Bad_Gateway, "Error", null));
                }
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }

    @DeleteMapping("/delete/{email}")
    public ResponseEntity<ResponseDTO> deleteUser(@PathVariable String email) {
        boolean isDeleted = userService.deleteUser(email);
        if (isDeleted) {
            return ResponseEntity.status(200)
                    .body(new ResponseDTO(VarList.OK, "User deleted successfully", null));
        } else {
            return ResponseEntity.status(404)
                    .body(new ResponseDTO(VarList.Not_Found, "User not found", null));
        }
    }

/*    @GetMapping("/api/v1/user/{email}")
    public ResponseEntity<Boolean> isExistByEmail(@PathVariable String email) {
       try {
            boolean exists = userService.existsByEmail(email);
            return ResponseEntity.status(HttpStatus.OK).body(exists);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
        System.out.println("hnbvnbvjhj");
        try {
            boolean isExist = userService.existByEmail(email);
            return ResponseEntity.status(HttpStatus.OK).body(isExist);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }*/

    @GetMapping("/check")
    public ResponseEntity<Boolean> isExistByEmail(@RequestParam String email) {
        boolean isExist = userService.existByEmail(email);
        return ResponseEntity.ok(isExist);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("test");
    }



}
