package com.example.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.dto.RegisterRequest;

import com.example.model.User;
import com.example.repository.UserRepository;

@Service

public class AuthService {

    @Autowired
	UserRepository userRepository;
    @Autowired
     PasswordEncoder passwordEncoder;
   
    public String register(RegisterRequest req) {

        if (userRepository.existsByEmail(req.getEmail())) {
            return "Email already registered";
        }

        User user = User.builder()
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                .username(req.getUsername())
                .phone(req.getPhone())
                .build();

        userRepository.save(user);

        return "User registered successfully";
    }
}

