package tn.esprit.microservice.kassil.services;

import tn.esprit.microservice.kassil.entities.Equipe;

import java.util.List;

public interface IEquipeService {
    public List<Equipe> retrieveAllEquipes();
    public Equipe addEquipe(Equipe e);
    public  void deleteEquipe(Integer idEquipe);
    public Equipe updateEquipe(Equipe e);
    public Equipe retrieveEquipe(Integer equipeId);
    public void evoluerEquipes();
}

