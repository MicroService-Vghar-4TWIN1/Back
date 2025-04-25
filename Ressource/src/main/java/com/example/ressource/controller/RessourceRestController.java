package com.example.ressource.controller;

import com.example.ressource.entity.ContratDTO;
import com.example.ressource.entity.Ressource;
import com.example.ressource.service.IRessourceService;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/Ressource")
public class RessourceRestController {
    @Autowired
    IRessourceService ressourceService;
    @GetMapping()
    public List<Ressource> getRessources() {
        return  ressourceService.retrieveAllRessources();

    }
    @GetMapping("/{ressource-id}")
    public Ressource retrieveRessource(@PathVariable("ressource-id") Long rId) {
        return   ressourceService.retrieveRessource(rId);

    }

    @PostMapping()
    public Ressource addRessource(@RequestBody Ressource r) {
        Ressource ressource = ressourceService.addRessource(r);
        return ressource;
    }

    @DeleteMapping("/{ressource-id}")
    public void removeRessource(@PathVariable("ressource-id") Long chId) {
        ressourceService.removeRessource(chId);
    }

    @PutMapping()
    public Ressource modifyRessource(@RequestBody Ressource r) {
        Ressource ressource = ressourceService.modifyRessource(r);
        return ressource;
    }

    @GetMapping("/stats")
    public Map<String, Long> getStatsParType() {
        return ressourceService.getNombreRessourcesParType();
    }

    @GetMapping("/contrat/{id}")
    public ContratDTO getContratById(@PathVariable Integer id){
        return this.ressourceService.getContratById(id);
    }

    @GetMapping("/contrats")
    public ResponseEntity<?> getAllContrats() {
        try {
            List<ContratDTO> contrats = ressourceService.getAllContrats();
            return ResponseEntity.ok(contrats);
        } catch (FeignException.Unauthorized e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Unauthorized to access contract service");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching contracts");
        }
    }
}