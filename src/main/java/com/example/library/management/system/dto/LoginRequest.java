package com.example.library.management.system.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginRequest {

    // ==========================================================
    // EMAIL
    // ==========================================================

    @NotBlank(message = "Email is required")
    @Size(
        max = 254,
        message = "Email cannot exceed 254 characters"
    )
    @Email(
        message = "Please enter a valid email address"
    )
    private String email;

    // ==========================================================
    // PASSWORD
    // ==========================================================

    @NotBlank(message = "Password is required")
    @Size(
        max = 30,
        message = "Password cannot exceed 30 characters"
    )
    private String password;

    // ==========================================================
    // DEFAULT CONSTRUCTOR
    // ==========================================================

    public LoginRequest() {
    }

    // ==========================================================
    // GETTERS & SETTERS
    // ==========================================================

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}