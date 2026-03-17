package com.mastermind.controller;

import com.mastermind.model.Tentativa;
import com.mastermind.service.TentativaServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tentativa")
public class TentativaControlador {
    @Autowired
    private TentativaServico tentativaServico;

    @PostMapping
    public ResponseEntity<Tentativa> criar(@RequestBody Tentativa tentativa) {
        Tentativa salvo = tentativaServico.salvar(tentativa);
        return ResponseEntity.ok(salvo);
    }

    @GetMapping
    public ResponseEntity<List<Tentativa>> listarTodas() {
        return ResponseEntity.ok(tentativaServico.listarTodas());
    }
}