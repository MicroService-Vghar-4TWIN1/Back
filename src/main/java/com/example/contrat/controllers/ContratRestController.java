package com.example.contrat.controllers;

import com.example.contrat.entities.Contrat;
import com.example.contrat.services.IContratService;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@RequestMapping("/Contrat")
public class ContratRestController {
	IContratService contratService;
	// http://localhost:8044/Kaddem/contrat/retrieve-all-contrats
	@GetMapping("/retrieve-all-contrats")
	public List<Contrat> getContrats() {
		List<Contrat> listContrats = contratService.retrieveAllContrats();
		return listContrats;
	}
	// http://localhost:8089/Kaddem/contrat/retrieve-contrat/8
	@GetMapping("/retrieve-contrat/{contrat-id}")
	public Contrat retrieveContrat(@PathVariable("contrat-id") Integer contratId) {
		return contratService.retrieveContrat(contratId);
	}

	// http://localhost:8089/Kaddem/econtrat/add-contrat
	@PostMapping("/add-contrat")
	public Contrat addContrat(@RequestBody Contrat c) {
		Contrat contrat = contratService.addContrat(c);
		return contrat;
	}

	// http://localhost:8089/Kaddem/contrat/remove-contrat/1
	@DeleteMapping("/remove-contrat/{contrat-id}")
	public void removeContrat(@PathVariable("contrat-id") Integer contratId) {
		contratService.removeContrat(contratId);
	}

	// http://localhost:8089/Kaddem/contrat/update-contrat
	@PutMapping("/update-contrat")
	public Contrat updateContrat(@RequestBody Contrat c) {
		Contrat contrat= contratService.updateContrat(c);
		return contrat;
	}

		/*@PutMapping(value = "/assignContratToEtudiant/{ce}/{nomE}/{prenomE}")
		public Contrat assignContratToEtudiant (Contrat ce, String nomE, String prenomE){
		return 	(contratService.affectContratToEtudiant(ce, nomE, prenomE));
		}*/




	//public float getChiffreAffaireEntreDeuxDate(Date startDate, Date endDate)

}


