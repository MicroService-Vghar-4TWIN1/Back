package com.example.ressource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
@EnableFeignClients(basePackages = "com.example.ressource.repository") // adjust the package
@SpringBootApplication
public class RessourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RessourceApplication.class, args);
    }

}
