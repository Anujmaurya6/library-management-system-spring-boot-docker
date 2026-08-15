package com.example.library.management.system.dto;

public class AuthResponse {

    private String message;

    private String tokenType;

    private String token;

    // ==========================================================
    // DEFAULT CONSTRUCTOR
    // ==========================================================

    public AuthResponse() {
    }

    // ==========================================================
    // PARAMETERIZED CONSTRUCTOR
    // ==========================================================

    public AuthResponse(
            String message,
            String tokenType,
            String token) {

        this.message = message;
        this.tokenType = tokenType;
        this.token = token;
    }

    // ==========================================================
    // GETTERS & SETTERS
    // ==========================================================

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}