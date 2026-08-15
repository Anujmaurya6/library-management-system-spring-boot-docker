package com.example.library.management.system.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.library.management.system.entity.User;
import com.example.library.management.system.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    // Constructor Injection
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ==========================================================
    // LOAD USER BY EMAIL
    // ==========================================================

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        // Database se user find karo
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "User not found with email : " + email
                        )
                );

        // Spring Security ke UserDetails object mein
        // database wale user ki information convert karo
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }
}