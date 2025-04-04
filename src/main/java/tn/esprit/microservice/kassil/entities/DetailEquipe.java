package tn.esprit.microservice.kassil.entities;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class DetailEquipe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDetailEquipe;

    private Integer salle;
    private String thematique;

    // Getters and Setters
    public Integer getIdDetailEquipe() { return idDetailEquipe; }
    public void setIdDetailEquipe(Integer idDetailEquipe) { this.idDetailEquipe = idDetailEquipe; }

    public Integer getSalle() { return salle; }
    public void setSalle(Integer salle) { this.salle = salle; }

    public String getThematique() { return thematique; }
    public void setThematique(String thematique) { this.thematique = thematique; }
}
