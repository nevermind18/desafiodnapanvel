package com.example.demo.service;

import com.example.demo.model.DnaEntity;
import com.example.demo.model.StatusEntity;
import com.example.demo.respository.DnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DnaService {

    @Autowired
    DnaRepository dnaRepository;

    public DnaEntity verificaRepeticao(DnaEntity dna) {
        String[] array = dna.getDna();
        int tamanho = array.length;

        for (int i = 0; i < tamanho; i++) {

            if (verificaSequencia(array[i])) {
                dna.setMutante(true);
            }
        }

        dnaRepository.save(dna);
        return dna;
    }


    private boolean verificaSequencia(String sequencia) {
        int contador = 1;
        char ultimoChar = sequencia.charAt(0);

        for (int i = 1; i < sequencia.length(); i++) {
            if (sequencia.charAt(i) == ultimoChar) {
                contador++;
                if (contador >= 4) {
                    return true;
                }
            } else {
                contador = 1;
                ultimoChar = sequencia.charAt(i);
            }
        }
        return false;
    }

    public StatusEntity status(){
        return new StatusEntity((int) dnaRepository.countByIsMutanteTrue(), (int) dnaRepository.countByIsMutanteFalse(), (double) dnaRepository.countByIsMutanteTrue() / dnaRepository.countByIsMutanteFalse());
    }
}