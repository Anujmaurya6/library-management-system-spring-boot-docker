package com.example.library.management.system.service;

import com.example.library.management.system.dto.AuthResponse;
import com.example.library.management.system.dto.LoginRequest;
import com.example.library.management.system.dto.SignupRequest;

public interface AuthService {

    void signup(SignupRequest request);

    AuthResponse login(LoginRequest request);
}