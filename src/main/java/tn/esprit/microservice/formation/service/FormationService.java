package tn.esprit.microservice.formation.service;

import tn.esprit.microservice.formation.entitiy.Formation;
import tn.esprit.microservice.formation.repository.FormationRepo;

import java.util.List;

public class FormationService implements IFormationService {

    FormationRepo formationRepo;
    public Formation addFormation(Formation formation)
    {
        return formationRepo.save(formation);
    }
    public Formation getFormation(int id)
    {
        return formationRepo.getReferenceById(id);
    }
    public List<Formation> getAllFormation()
    {
        return formationRepo.findAll();
    }

    public Formation updateFormation(Formation formation)
    {
        return formationRepo.save(formation);
    }
    public void deleteFormation(int id)
    {
        formationRepo.deleteById(id);
    }

}
