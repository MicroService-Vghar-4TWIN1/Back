package tn.esprit.microservice.formation.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tn.esprit.microservice.formation.entitiy.Formation;
import tn.esprit.microservice.formation.service.FormationService;
import tn.esprit.microservice.formation.service.IFormationService;

import java.util.List;

@RestController
@AllArgsConstructor
@Controller
class FormationController {
    @Autowired
    private FormationService formationService;

    @PostMapping
    public ResponseEntity<Formation> addFormation(@RequestBody Formation formation) {
        System.out.println("Received formation: " + formation); // Debugging line
        if (formation == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        formationService.addFormation(formation);
        return ResponseEntity.status(HttpStatus.CREATED).body(formation);
    }
    @GetMapping
            public ResponseEntity<List<Formation>> getAllFormations()
    {
        return ResponseEntity.status(HttpStatus.OK).body(formationService.getAllFormation());

    }

    @GetMapping("/{id}")
    Formation getFormation(@PathVariable int id) {
        return formationService.getFormation(id);
    }
    @DeleteMapping("/{id}")
    void deleteFormation(@PathVariable int id) {
        formationService.deleteFormation(id);
    }
    @PutMapping
    Formation updateFormation(@RequestBody Formation formation) {
        return formationService.updateFormation(formation);
    }


}
