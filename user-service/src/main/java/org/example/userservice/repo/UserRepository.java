package org.example.userservice.repo;

import org.example.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User , String> {
    boolean existsByEmail(String email);
    User getReferenceByEmail(String email);

    User findByEmail(String email);

    boolean existsByUsername(String username);

    User findByUsername(String username);
}
