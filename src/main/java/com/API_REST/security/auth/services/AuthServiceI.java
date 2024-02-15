package com.API_REST.security.auth.services;

import com.API_REST.security.auth.model.AuthResponse;
import com.API_REST.security.auth.model.LoginRequest;
import com.API_REST.security.auth.model.RegisterRequest;

public interface AuthServiceI {
    AuthResponse login(LoginRequest request);
    AuthResponse register(RegisterRequest request);
}
