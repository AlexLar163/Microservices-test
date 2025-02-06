package com.alexlar163.authentication_server.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "admin_password";
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println(encodedPassword);
    }
}