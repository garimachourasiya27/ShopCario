package com.example.service;
import com.example.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.dto.LoginRequest;
import com.example.model.User;
import com.example.repository.UserRepository;

@Service
public class AuthService {

    private final JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    AuthService(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

     // Register
    public String register(com.example.dto.RegisterRequest req) {

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
    
    public String login(LoginRequest req) {

        String identifier = req.getEmail();

        User user = userRepository
                .findByEmailOrPhoneOrUsername(identifier, identifier, identifier)
                .orElseThrow(() -> new RuntimeException("Invalid Email"));

        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid Password");
        }

        return jwtUtil.generateToken(user.getEmail());
    }
}
    
    
    
    

//    // LOGIN
//    public String login(LoginRequest req) {
//
//        User user = userRepository.findByEmail(req.getEmail())
//                .orElseThrow(() -> new RuntimeException("Invalid Email"));
//
//        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
//            return "Invalid email or password";
//        }
//
//        return "Login successful";
//    }


