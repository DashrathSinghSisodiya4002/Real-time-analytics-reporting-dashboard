package com.Real_time_analytics_reporting_dashboard.Controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Real_time_analytics_reporting_dashboard.Dto.AuthResponse;
import com.Real_time_analytics_reporting_dashboard.Dto.LoginRequest;
import com.Real_time_analytics_reporting_dashboard.Dto.RegisterRequest;
import com.Real_time_analytics_reporting_dashboard.Service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @RequestBody RegisterRequest request) {

        return ResponseEntity.ok(
                authService.register(request)
        );
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody LoginRequest request) {

        return ResponseEntity.ok(
                authService.login(request)
        );
    }
}