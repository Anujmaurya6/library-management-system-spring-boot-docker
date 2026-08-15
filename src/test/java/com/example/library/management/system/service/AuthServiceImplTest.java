package com.example.library.management.system.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.library.management.system.dto.AuthResponse;
import com.example.library.management.system.dto.LoginRequest;
import com.example.library.management.system.dto.SignupRequest;
import com.example.library.management.system.entity.User;
import com.example.library.management.system.enums.Role;
import com.example.library.management.system.repository.UserRepository;
import com.example.library.management.system.security.JwtService;
import com.example.library.management.system.impl.AuthServiceImpl;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    // ==========================================================
    // MOCK DEPENDENCIES
    // ==========================================================

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtService jwtService;

    // ==========================================================
    // REAL SERVICE
    // ==========================================================

    @InjectMocks
    private AuthServiceImpl service;

    // ==========================================================
    // SIGNUP SUCCESS
    // ==========================================================

    @Test
    void signup_shouldCreateUser() {

        SignupRequest request =
                new SignupRequest();

        request.setName("Anuj Maurya");
        request.setEmail("anuj@gmail.com");
        request.setPassword("Anuj@123");

        when(userRepository.existsByEmail(
                "anuj@gmail.com"
        )).thenReturn(false);

        when(passwordEncoder.encode(
                "Anuj@123"
        )).thenReturn("hashedPassword");

        service.signup(request);

        verify(userRepository, times(1))
                .existsByEmail("anuj@gmail.com");

        verify(passwordEncoder, times(1))
                .encode("Anuj@123");

        verify(userRepository, times(1))
                .save(any(User.class));
    }

    // ==========================================================
    // SIGNUP - DUPLICATE EMAIL
    // ==========================================================

    @Test
    void signup_shouldThrowException_whenEmailExists() {

        SignupRequest request =
                new SignupRequest();

        request.setName("Anuj Maurya");
        request.setEmail("anuj@gmail.com");
        request.setPassword("Anuj@123");

        when(userRepository.existsByEmail(
                "anuj@gmail.com"
        )).thenReturn(true);

        RuntimeException exception =
                assertThrows(
                        RuntimeException.class,
                        () -> service.signup(request)
                );

        assertEquals(
                "Email already registered",
                exception.getMessage()
        );

        verify(userRepository, times(1))
                .existsByEmail("anuj@gmail.com");

        verify(userRepository, never())
                .save(any(User.class));

        verify(passwordEncoder, never())
                .encode(anyString());
    }

    // ==========================================================
    // LOGIN SUCCESS
    // ==========================================================

    @Test
    void login_shouldReturnJwtToken() {

        LoginRequest request =
                new LoginRequest();

        request.setEmail("anuj@gmail.com");
        request.setPassword("Anuj@123");

        when(jwtService.generateToken(
                "anuj@gmail.com"
        )).thenReturn("dummy-jwt-token");

        AuthResponse response =
                service.login(request);

        assertNotNull(response);

        assertEquals(
                "Login successful",
                response.getMessage()
        );

        assertEquals(
                "Bearer",
                response.getTokenType()
        );

        assertEquals(
                "dummy-jwt-token",
                response.getToken()
        );

        verify(authenticationManager, times(1))
                .authenticate(
                        any(
                            UsernamePasswordAuthenticationToken.class
                        )
                );

        verify(jwtService, times(1))
                .generateToken("anuj@gmail.com");
    }
}