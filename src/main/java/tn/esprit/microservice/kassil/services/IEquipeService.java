package tn.esprit.microservice.kassil.services;

import tn.esprit.microservice.kassil.entities.Equipe;
import java.util.List;

public interface IEquipeService {
    List<Equipe> retrieveAllEquipes();
    Equipe addEquipe(Equipe e);
    void deleteEquipe(Integer idEquipe);
    Equipe updateEquipe(Equipe e);
    Equipe retrieveEquipe(Integer equipeId);
    void evoluerEquipes();
}
