package org.example.userservice.service;

import org.example.userservice.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface UserService {
    int saveUser(UserDTO userDTO);

    List<UserDTO> getAllUsers();

    boolean deleteUser(String email);

    boolean existByEmail(String email);
    UserDTO getUserByEmail(String email);
    UserDTO getUserDTOByToken(String token);

    UserDTO loadUserDetailsByUsername(String username);


    UserDTO loadUserDetailsByEmail(String email);

//    UserDetails loadUserByUserEmail(String email);

    UserDetails loadUserByUsername(String email);
}
