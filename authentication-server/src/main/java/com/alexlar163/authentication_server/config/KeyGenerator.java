package com.alexlar163.authentication_server.config;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;

public class KeyGenerator {
    public static void main(String[] args) {
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String encodedKey = java.util.Base64.getEncoder().encodeToString(key.getEncoded());
        System.out.println(encodedKey);
    }
}