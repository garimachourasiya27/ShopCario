package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
//for find by any identifier
    Optional<User> findByEmailOrPhoneOrUsername(
            String email,
            String phone,
            String username
    );
}

