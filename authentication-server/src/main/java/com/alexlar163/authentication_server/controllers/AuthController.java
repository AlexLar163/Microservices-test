package com.alexlar163.authentication_server.controllers;

import com.alexlar163.authentication_server.dto.AuthenticationRequest;
import com.alexlar163.authentication_server.dto.AuthenticationResponse;
import com.alexlar163.authentication_server.entity.UserEntity;
import com.alexlar163.authentication_server.service.JwtService;
import com.alexlar163.authentication_server.service.UserDetailsServiceImpl;
import com.alexlar163.authentication_server.service.UserServiceImpl;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationmanager;
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final UserServiceImpl userServiceImpl;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationmanager, UserDetailsServiceImpl userDetailsServiceImpl, UserServiceImpl userServiceImpl, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.authenticationmanager = authenticationmanager;
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.userServiceImpl = userServiceImpl;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthenticationRequest request) {
            UserEntity newUser = new UserEntity();
            newUser.setUsername(request.getUsername());
            newUser.setPassword(passwordEncoder.encode(request.getPassword()));
            userServiceImpl.save(newUser);

            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> createToken(@RequestBody AuthenticationRequest request) {
        try {
            authenticationmanager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        } catch (Exception e) {
            System.out.println("Invalid username or password");
            LoggerFactory.getLogger(AuthController.class).error("Invalid username or password", e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        final UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(request.getUsername());
        final String jwtToken = jwtService.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwtToken));
    }
}
