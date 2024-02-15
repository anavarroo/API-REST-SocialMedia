package com.API_REST.security.auth.services;

import com.API_REST.persistence.model.Role;
import com.API_REST.persistence.model.User;
import com.API_REST.persistence.repository.UserRepositoryI;
import com.API_REST.security.auth.model.AuthResponse;
import com.API_REST.security.auth.model.LoginRequest;
import com.API_REST.security.auth.model.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements AuthServiceI {

    @Autowired
    private UserRepositoryI userRepository;

    @Autowired
    private JWTServiceI jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;


    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        User user=userRepository.findByUsername(request.getUsername()).orElseThrow();
        return new AuthResponse(jwtService.getToken(user));
    }

    public AuthResponse register(RegisterRequest request) {
        User user = new User(request.getUsername(), request.getEmail(), passwordEncoder.encode(request.getPassword()), request.getDescription(),Role.USER);
        userRepository.save(user);
        return new AuthResponse(jwtService.getToken(user));
    }


}
