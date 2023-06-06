package com.peaceofmind.authentication.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.peaceofmind.authentication.controller.UserController;
import com.peaceofmind.authentication.exception_handling.AuthenticationFailedException;
import com.peaceofmind.authentication.model.User;
import com.peaceofmind.authentication.repository.IUserRepo;
import com.peaceofmind.authentication.security.JWTService;
import com.peaceofmind.authentication.service.imp.UserServiceImp;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserServiceImp userService;
    private final IUserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.getFirstname())
                .lastName(request.getLastname())
                .username(request.getUsername())
                .email(request.getEmail())
                .roles(request.getRoles())
                .password(passwordEncoder.encode(request.getPassword()))
                .is_active(true)
                .build();
        userService.createUser(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        System.out.println("just hitting");
    
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (AuthenticationException ex) {
            // Handle authentication exception
            // You can log the error, return a custom error response, or perform any other necessary actions
            // For example:
            System.out.println("Authentication failed: " + ex.getMessage());
            throw new AuthenticationFailedException("Authentication failed");
        }
    
        System.out.println("before finding username");
        var user = userRepo.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Username Not Found"));
    
        System.out.println("after finding username");
    
        var jwtToken = jwtService.generateToken(user);
        System.out.println("after generating token");
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }
    

}
