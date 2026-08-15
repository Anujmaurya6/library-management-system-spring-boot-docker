package com.example.library.management.system.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.library.management.system.dto.AuthResponse;
import com.example.library.management.system.dto.LoginRequest;
import com.example.library.management.system.dto.SignupRequest;
import com.example.library.management.system.entity.User;
import com.example.library.management.system.enums.Role;
import com.example.library.management.system.repository.UserRepository;
import com.example.library.management.system.security.JwtService;
import com.example.library.management.system.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    // ==========================================================
    // CONSTRUCTOR INJECTION
    // ==========================================================

    public AuthServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JwtService jwtService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    // ==========================================================
    // SIGNUP
    // ==========================================================

    @Override
    public void signup(SignupRequest request) {

        // Check whether email already exists
        if (userRepository.existsByEmail(request.getEmail())) {

            throw new RuntimeException(
                    "Email already registered"
            );
        }

        // Create new User Entity
        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        // NEVER store plain password
        user.setPassword(
                passwordEncoder.encode(
                        request.getPassword()
                )
        );

        // Default role
        user.setRole(Role.USER);

        // Save user into database
        userRepository.save(user);
    }

    // ==========================================================
    // LOGIN
    // ==========================================================

    @Override
    public AuthResponse login(LoginRequest request) {

        // Authenticate email + password
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // Generate JWT
        String token =
                jwtService.generateToken(
                        request.getEmail()
                );

        // Return JWT response
        return new AuthResponse(
                "Login successful",
                "Bearer",
                token
        );
    }
}