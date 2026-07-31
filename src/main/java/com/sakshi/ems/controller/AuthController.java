package com.sakshi.ems.controller;

import com.sakshi.ems.dto.auth.AuthResponse;
import com.sakshi.ems.dto.auth.LoginRequest;
import com.sakshi.ems.dto.auth.RegisterRequest;
import com.sakshi.ems.response.ApiResponse;
import com.sakshi.ems.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> register(
            @Valid @RequestBody RegisterRequest request) {

        String message = authService.register(request);

        ApiResponse<String> response = ApiResponse.<String>builder()
                .success(true)
                .message(message)
                .timestamp(LocalDateTime.now())
                .data(null)
                .meta(null)
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(
            @Valid @RequestBody LoginRequest request) {

        AuthResponse authResponse = authService.login(request);

        ApiResponse<AuthResponse> response =
                ApiResponse.<AuthResponse>builder()
                        .success(true)
                        .message("Login successful")
                        .timestamp(LocalDateTime.now())
                        .data(authResponse)
                        .meta(null)
                        .build();

        return ResponseEntity.ok(response);
    }
}