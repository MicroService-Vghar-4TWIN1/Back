package com.example.ressource.controller;

import com.example.ressource.entity.Ressource;
import com.example.ressource.service.IRessourceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/Ressource")
@CrossOrigin(origins = "http://localhost:4200")
public class RessourceRestController {

    IRessourceService ressourceService;
    @GetMapping()
    public List<Ressource> getRessources() {
        return  ressourceService.retrieveAllRessources();

    }
    @GetMapping("/{ressource-id}")
    public Ressource retrieveRessource(@PathVariable("ressource-id") Long rId) {
        return   ressourceService.retrieveRessource(rId);

    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Ressource addRessource(
            @RequestPart("ressource") String rString,
            @RequestPart(value = "file", required = false) MultipartFile file) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        Ressource r = objectMapper.readValue(rString, Ressource.class);

        // 🛠 Vérification du fichier
        if (file != null && !file.isEmpty()) {
            System.out.println("Fichier reçu : " + file.getOriginalFilename() + " (" + file.getSize() + " octets)");
            r.setPdf(file.getOriginalFilename());
        } else {
            System.out.println("Aucun fichier reçu !");
        }

        return ressourceService.addRessource(r);
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


}
