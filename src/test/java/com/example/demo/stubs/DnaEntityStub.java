package com.example.demo.stubs;

import com.example.demo.model.DnaEntity;

public class DnaEntityStub {

    public static DnaEntity stubLinhaMutante() {

        String[] dna = {
                "aggcga",
                "cagtgc",
                "ttggtt",
                "aggcgg",
                "gcgtca",
                "tcactg"};

        return new DnaEntity(
                "teste",
                dna,
                true
        );
    }
}
