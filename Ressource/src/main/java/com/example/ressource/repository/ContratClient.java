package com.example.ressource.repository;

import com.example.ressource.config.FeignClientConfig;
import com.example.ressource.entity.ContratDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "CONTRAT", configuration = FeignClientConfig.class)// Eureka will route it
public interface ContratClient {

    @GetMapping("/Contrat/retrieve-all-contrats")
    List<ContratDTO> getContrats();

    @GetMapping("/Contrat/retrieve-contrat/{id}")
    ContratDTO retrieveContrat(@PathVariable("id") Integer id);

}
