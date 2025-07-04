package com.esprit.microservice.departementmicroservice.services.Impl;

import com.esprit.microservice.departementmicroservice.entities.Departement;
import com.esprit.microservice.departementmicroservice.entities.UniversityDto;
import com.esprit.microservice.departementmicroservice.repositories.DepartementRepository;
import com.esprit.microservice.departementmicroservice.repositories.UniversityClient;
import com.esprit.microservice.departementmicroservice.services.IService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class DepartementService implements IService{
    private final UniversityClient universityClient;
    @Autowired
    private  DepartementRepository departementRepository;

    @Override
    public List<Departement> retrieveAllDepartements(){
        return  this.departementRepository.findAll();
    }
    @Override
    public Departement addDepartement(Departement d) {
        // Check university exists
        try {
            var university = universityClient.getUniversityById(d.getIdUniversite());
            log.info("Associated University: {}", university.getNomUniv());
        } catch (Exception e) {
            throw new RuntimeException("University with ID " + d.getIdUniversite() + " not found.");
        }

        d.setCreatedDate(LocalDate.now());
        return this.departementRepository.save(d);
    }

    @Override
    public Departement updateDepartement(Departement d, Integer id) {
        Departement departement = this.departementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Departement not found with id: " + id));

        departement.setNomDepart(d.getNomDepart());
        departement.setDescription(d.getDescription());
        departement.setCode(d.getCode());
        departement.setCreatedDate(LocalDate.now());
        departement.setEmail(d.getEmail());
        departement.setPhone(d.getPhone());
        departement.setIdUniversite(d.getIdUniversite());
        departement.setActive(d.isActive());

        return this.departementRepository.save(departement);
    }



    public  Departement retrieveDepartement (Integer idDepart){
        return this.departementRepository.findById(idDepart).get();
    }
    public  void deleteDepartement(Integer idDepartement){
        Departement d=retrieveDepartement(idDepartement);
        this.departementRepository.delete(d);
    }

    public UniversityDto getUniversityById(Integer id){
        return this.universityClient.getUniversityById(id);
    }



}