package com.example.demo.controller;

import com.example.demo.model.DnaEntity;
import com.example.demo.model.StatusEntity;
import com.example.demo.service.DnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DnaController {

    @Autowired
    DnaService dnaService;

    @PostMapping("/mutant")
    public DnaEntity isMutant(@RequestBody DnaEntity dnaRequest) {
        return dnaService.verificaRepeticao(dnaRequest);
    }

    @GetMapping("/status")
    public StatusEntity status(){
        return dnaService.status();
    }
}