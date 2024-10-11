package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "DNA")
public class DnaEntity {

    @Id
    private String id;
    private String[] dna;
    private boolean isMutante;

    public boolean isMutante() {
        return isMutante;
    }

    public void setMutante(boolean mutante) {
        isMutante = mutante;
    }

    public String[] getDna() {
        return dna;
    }

    public void setDna(String[] dna) {
        this.dna = dna;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DnaEntity(String id, String[] dna, boolean isMutante) {
        this.id = id;
        this.dna = dna;
        this.isMutante = isMutante;
    }

    public DnaEntity(String[] dna, boolean isMutante) {
        this.dna = dna;
        this.isMutante = isMutante;
    }

    public DnaEntity() {
    }
}
