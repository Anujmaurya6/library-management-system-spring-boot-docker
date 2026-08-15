package com.example.library.management.system.entity;

import com.example.library.management.system.enums.Role;

import jakarta.persistence.*;

@Entity
@Table(
    name = "users",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_users_email",
            columnNames = "email"
        )
    }
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ==========================================================
    // NAME
    // ==========================================================

    @Column(
        nullable = false,
        length = 50
    )
    private String name;

    // ==========================================================
    // EMAIL
    // ==========================================================

    @Column(
        nullable = false,
        unique = true,
        length = 254
    )
    private String email;

    // ==========================================================
    // PASSWORD
    // ==========================================================
    // Password database me BCrypt hash ke form me store hoga.
    // Isliye yaha password ki @Pattern validation nahi lagani.

    @Column(
        nullable = false,
        length = 60
    )
    private String password;

    // ==========================================================
    // ROLE
    // ==========================================================

    @Enumerated(EnumType.STRING)
    @Column(
        nullable = false,
        length = 20
    )
    private Role role;

    // ==========================================================
    // DEFAULT CONSTRUCTOR
    // ==========================================================

    public User() {
    }

    // ==========================================================
    // PARAMETERIZED CONSTRUCTOR
    // ==========================================================

    public User(
            Long id,
            String name,
            String email,
            String password,
            Role role) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // ==========================================================
    // GETTERS & SETTERS
    // ==========================================================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}