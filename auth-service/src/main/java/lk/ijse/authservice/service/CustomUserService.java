package lk.ijse.authservice.service;

import lk.ijse.authservice.dto.ResponseDTO;
import lk.ijse.authservice.dto.UserDTO;
import lk.ijse.authservice.feign.UserInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserService implements UserDetailsService {
    private final UserInterface userInterface;

    public CustomUserService(UserInterface userInterface) {
        this.userInterface = userInterface;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            ResponseEntity<UserDTO> userByEmail = userInterface.getUserByEmail(username);
            UserDTO userDTO = userByEmail.getBody();
            if (userDTO == null) {
                throw new UsernameNotFoundException("User not found for username: " + username);
            }
            return new org.springframework.security.core.userdetails.User(
                    userDTO.getEmail(),
                    userDTO.getPassword(),
                    List.of(new org.springframework.security.core.authority.SimpleGrantedAuthority(userDTO.getRole()))
            );
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found for username: " + username);
        }


    }
    public UserDTO getUser(String email) {
        try {
            ResponseEntity<UserDTO> userByEmail = userInterface.getUserByEmail(email);
            UserDTO userDTO = userByEmail.getBody();
            if (userDTO == null) {
                throw new UsernameNotFoundException("User not found for email: " + email);
            }
            return userDTO;
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found for email: " + email);
        }
    }
}
