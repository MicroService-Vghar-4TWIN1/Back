package tn.esprit.microservice.kassil.entities;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class Equipe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEquipe;

    private String nomEquipe;

    @Enumerated(EnumType.STRING)
    private Niveau niveau;

    private Integer detailEquipeId; // Foreign key as Integer

    // Getters and Setters
    public Integer getIdEquipe() { return idEquipe; }
    public void setIdEquipe(Integer idEquipe) { this.idEquipe = idEquipe; }

    public String getNomEquipe() { return nomEquipe; }
    public void setNomEquipe(String nomEquipe) { this.nomEquipe = nomEquipe; }

    public Niveau getNiveau() { return niveau; }
    public void setNiveau(Niveau niveau) { this.niveau = niveau; }

    public Integer getDetailEquipeId() { return detailEquipeId; }
    public void setDetailEquipeId(Integer detailEquipeId) { this.detailEquipeId = detailEquipeId; }
}
