package com.API_REST.security.auth.controllers;

import com.API_REST.security.auth.model.AuthResponse;
import com.API_REST.security.auth.model.LoginRequest;
import com.API_REST.security.auth.model.RegisterRequest;
import com.API_REST.security.auth.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping(value = "/login")
    @Operation(summary = "Loguear usuario")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }



    @PostMapping(value = "/register")
    @Operation(summary = "Registrar usuario")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }
}
