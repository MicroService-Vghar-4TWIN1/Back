package com.example.apigetway.config;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Configuration
public class GatewayCorsConfig {

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public GlobalFilter corsFilter() {
        return (exchange, chain) -> {
            ServerHttpResponse response = exchange.getResponse();
            HttpHeaders headers = response.getHeaders();

            // Set CORS headers
            headers.set("Access-Control-Allow-Origin", "http://localhost:4200");
            headers.set("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            headers.set("Access-Control-Allow-Headers", "Authorization, Content-Type");
            headers.set("Access-Control-Expose-Headers", "Authorization");
            headers.set("Access-Control-Allow-Credentials", "true");
            headers.set("Access-Control-Max-Age", "3600");

            // Handle preflight requests
            if (exchange.getRequest().getMethod() == HttpMethod.OPTIONS) {
                response.setStatusCode(HttpStatus.NO_CONTENT);
                return Mono.empty();
            }

            return chain.filter(exchange);
        };
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE + 1)
    public  GlobalFilter redirectFilter() {
        return (exchange, chain) -> {
            if (exchange.getResponse().getStatusCode() == HttpStatus.FOUND) {
                // Block redirects for API requests
                exchange.getResponse().setStatusCode(HttpStatus.BAD_GATEWAY);
                return Mono.empty();
            }
            return chain.filter(exchange);
        };
    }
}