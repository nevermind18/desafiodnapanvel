package com.example.demo.service;

import com.example.demo.model.DnaEntity;
import com.example.demo.model.StatusEntity;
import com.example.demo.respository.DnaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class DnaServiceTest {

    @InjectMocks
    private DnaService dnaService; // Classe que estamos testando

    @Mock
    private DnaRepository dnaRepository; // Mock do repositório

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa os mocks
    }

    @Test
    public void testVerificaRepeticaoMutante() {
        // Dado
        String[] dnaSequence = {
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        };
        DnaEntity dnaEntity = new DnaEntity("teste-mutante", dnaSequence, false);

        // Quando
        DnaEntity result = dnaService.verificaRepeticao(dnaEntity);

        // Então
        assertTrue(result.isMutante());
    }

    @Test
    public void testVerificaRepeticaoNaoMutante() {
        // Dado
        String[] dnaSequence = {
                "ATGCGA",
                "CAGTCC",
                "TTATGT",
                "AGACGG",
                "CCTCTA",
                "TCACTG"
        };
        DnaEntity dnaEntity = new DnaEntity("teste-nao-mutante", dnaSequence, false);

        // Quando
        DnaEntity result = dnaService.verificaRepeticao(dnaEntity);

        // Então
        assertFalse(result.isMutante());
    }

    @Test
    public void testStatus() {
        // Mockando o comportamento do repositório
        when(dnaRepository.countByIsMutanteTrue()).thenReturn(40L);
        when(dnaRepository.countByIsMutanteFalse()).thenReturn(100L);

        // Chamando o método
        StatusEntity status = dnaService.status();

        // Verificações
        assertEquals(40, status.getCount_mutant_dna());
        assertEquals(100, status.getCount_human_dna());
        assertEquals(0.4, status.getRatio()); // Tolerância de precisão
    }
}