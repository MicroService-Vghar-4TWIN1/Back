package com.esprit.microservice.departementmicroservice.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UniversityDto {
    private Integer idUniv;
    private String nomUniv;
    private Double latitude;
    private Double longitude;
}