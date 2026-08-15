package com.example.library.management.system.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class SignupRequest {

    // ==========================================================
    // NAME VALIDATION
    // ==========================================================

    @NotBlank(message = "Name is required")
    @Size(
        min = 2,
        max = 50,
        message = "Name must be between 2 and 50 characters"
    )
    @Pattern(
        regexp = "^[a-zA-Z]+(?:[ '-][a-zA-Z]+)*$",
        message = "Name can contain only letters, spaces, hyphens or apostrophes"
    )
    private String name;

    // ==========================================================
    // EMAIL VALIDATION
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
    // PASSWORD VALIDATION
    // ==========================================================

    @NotBlank(message = "Password is required")
    @Size(
        min = 8,
        max = 30,
        message = "Password must be between 8 and 30 characters"
    )
    @Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,30}$",
        message = "Password must contain at least one uppercase letter, one lowercase letter, one number and one special character"
    )
    private String password;

    // ==========================================================
    // DEFAULT CONSTRUCTOR
    // ==========================================================

    public SignupRequest() {
    }

    // ==========================================================
    // GETTERS & SETTERS
    // ==========================================================

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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