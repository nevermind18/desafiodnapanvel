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

            if (verificaSequencia(array[i]) || verificaColuna(array, i)) {
                dna.setMutante(true);
            }
        }

        if (verificaDiagonais(array)) {
            dna.setMutante(true);
        }

        dnaRepository.save(dna);
        return dna;
    }

    private boolean verificaColuna(String[] array, int coluna) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i].charAt(coluna));
        }
        return verificaSequencia(sb.toString());
    }

    private boolean verificaDiagonais(String[] array) {
        int n = array.length;

        for (int i = 0; i < n; i++) {
            if (verificaDiagonalPrincipal(array, 0, i) || verificaDiagonalSecundaria(array, 0, n - 1 - i)) {
                return true;
            }
        }

        for (int i = 1; i < n; i++) {
            if (verificaDiagonalPrincipal(array, i, 0) || verificaDiagonalSecundaria(array, i, n - 1)) {
                return true;
            }
        }

        return false;
    }

    private boolean verificaDiagonalPrincipal(String[] array, int linha, int coluna) {
        StringBuilder sb = new StringBuilder();
        while (linha < array.length && coluna < array.length) {
            sb.append(array[linha].charAt(coluna));
            linha++;
            coluna++;
        }
        return verificaSequencia(sb.toString());
    }

    private boolean verificaDiagonalSecundaria(String[] array, int linha, int coluna) {
        StringBuilder sb = new StringBuilder();
        while (linha < array.length && coluna >= 0) {
            sb.append(array[linha].charAt(coluna));
            linha++;
            coluna--;
        }
        return verificaSequencia(sb.toString());
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