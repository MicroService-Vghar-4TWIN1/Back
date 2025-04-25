package com.example.ressource.service;

import com.example.ressource.entity.ContratDTO;
import com.example.ressource.entity.Ressource;
import com.example.ressource.repository.ContratClient;
import com.example.ressource.repository.RessourceRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
public class RessourceServiceImpl implements IRessourceService {
    @Autowired
    RessourceRepository ressourceRepository;
    private final ContratClient contratClient;

    @Override
    public List<Ressource> retrieveAllRessources() {
        return ressourceRepository.findAll();
    }

    @Override
    public Ressource retrieveRessource(Long rId) {
        return ressourceRepository.findById(rId).get();
    }

    @Override
    public Ressource addRessource(Ressource r) {
        return ressourceRepository.save(r);
    }

    @Override
    public void removeRessource(Long rId) {
        ressourceRepository.deleteById(rId);

    }

    @Override
    public Ressource modifyRessource(Ressource r) {
        return ressourceRepository.save(r);
    }

    @Override
    public Map<String, Long> getNombreRessourcesParType() {
        List<Object[]> result = ressourceRepository.countRessourcesByType();
        Map<String, Long> stats = new HashMap<>();
        for (Object[] row : result) {
            stats.put(row[0].toString(), (Long) row[1]);
        }
        return stats;
    }
    @Override
    public ContratDTO getContratById(Integer id){
        return this.contratClient.retrieveContrat(id);
    }

    @Override
    public List<ContratDTO> getAllContrats() {
        System.out.println(this.contratClient.getContrats());
        return this.contratClient.getContrats();    }


}