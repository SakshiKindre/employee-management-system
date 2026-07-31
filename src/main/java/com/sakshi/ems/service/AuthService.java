package com.sakshi.ems.service;

import com.sakshi.ems.dto.auth.AuthResponse;
import com.sakshi.ems.dto.auth.LoginRequest;
import com.sakshi.ems.dto.auth.RegisterRequest;

public interface AuthService {

    String register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}