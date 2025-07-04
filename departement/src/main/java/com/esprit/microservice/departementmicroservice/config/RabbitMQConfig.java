package com.esprit.microservice.departementmicroservice.config;


import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String REQUEST_QUEUE = "department.financialAid.request";

    @Bean
    public Queue requestQueue() {
        return new Queue(REQUEST_QUEUE);
    }
}