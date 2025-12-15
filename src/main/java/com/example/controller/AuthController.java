package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.dto.RegisterRequest;

import com.example.service.AuthService;

//import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
//@RequiredArgsConstructor
public class AuthController {
@Autowired
    AuthService authService;

    
    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest req) {
    return authService.register(req);
    }

  
}