package com.esprit.microservice.departementmicroservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Departement implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idDepart;
    @JsonProperty("nomDepart")
    private String nomDepart;

    public Integer getIdDepart() {
        return idDepart;
    }

    public void setIdDepart(Integer idDepart) {
        this.idDepart = idDepart;
    }

    public String getNomDepart() {
        return nomDepart;
    }

    public void setNomDepart(String nomDepart) {
        this.nomDepart = nomDepart;
    }
}