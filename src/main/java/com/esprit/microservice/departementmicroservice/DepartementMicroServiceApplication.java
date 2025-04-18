package com.esprit.microservice.departementmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "com.esprit.microservice.departementmicroservice.repositories") // adjust the package

@SpringBootApplication
public class DepartementMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DepartementMicroServiceApplication.class, args);
	}

}
