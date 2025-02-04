package com.alexlar163.api_gateway.filters;

import com.alexlar163.api_gateway.utils.JwtUtil;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class JwtFilter implements GlobalFilter {
    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);
    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        logger.info("Request intercepted by API Gateway: " + request.getPath().toString());

        // Permitir el acceso libre al servicio de autenticación
        if (request.getPath().toString().startsWith("/auth")) {
            logger.info("Path starts with /auth, allowing request");
            return chain.filter(exchange);
        }

        // Obtener token del header Authorization
        String token = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (token == null || !token.startsWith("Bearer ")) {
            logger.warn("Token is missing or incorrect format");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        // Extraer JWT sin "Bearer "
        String jwtToken = token.substring(7);

        // Validar token
        if (!jwtUtil.validateToken(jwtToken)) {
            logger.warn("Invalid token");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        // Token válido, continuar con la solicitud
        logger.info("Token is valid, allowing request");
        return chain.filter(exchange);
    }
}
