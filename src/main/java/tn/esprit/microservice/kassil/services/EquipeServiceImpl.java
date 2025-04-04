package tn.esprit.microservice.kassil.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.microservice.kassil.entities.Equipe;
import tn.esprit.microservice.kassil.entities.Niveau;
import tn.esprit.microservice.kassil.repositories.EquipeRepository;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class EquipeServiceImpl implements IEquipeService {

    private final EquipeRepository equipeRepository;

    public List<Equipe> retrieveAllEquipes() {
        return (List<Equipe>) equipeRepository.findAll();
    }

    public Equipe addEquipe(Equipe e) {
        return equipeRepository.save(e);
    }

    public void deleteEquipe(Integer idEquipe) {
        equipeRepository.deleteById(idEquipe);
    }

    public Equipe retrieveEquipe(Integer equipeId) {
        return equipeRepository.findById(equipeId).orElse(null);
    }

    public Equipe updateEquipe(Equipe e) {
        return equipeRepository.save(e);
    }

    public void evoluerEquipes() {
        List<Equipe> equipes = (List<Equipe>) equipeRepository.findAll();
        for (Equipe equipe : equipes) {
            if (equipe.getNiveau() == Niveau.JUNIOR) {
                equipe.setNiveau(Niveau.SENIOR);
                equipeRepository.save(equipe);
            } else if (equipe.getNiveau() == Niveau.SENIOR) {
                equipe.setNiveau(Niveau.EXPERT);
                equipeRepository.save(equipe);
            }
        }
    }
}
