package com.esprit.microservice.departementmicroservice.repositories;

import com.esprit.microservice.departementmicroservice.entities.UniversityDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "UNIVERSITE") // Eureka will route it
public interface UniversityClient {

    @GetMapping("/universite/retrieve-all-universites")
    List<UniversityDto> getAllUniversities();

    @GetMapping("/universite/retrieve-universite/{id}")
    UniversityDto getUniversityById(@PathVariable("id") Integer id);
}
