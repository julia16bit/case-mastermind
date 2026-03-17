package com.mastermind.controller;

import com.mastermind.model.Partida;
import com.mastermind.service.PartidaServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/partida")
public class PartidaControlador {
    @Autowired
    private PartidaServico partidaServico;

    @PostMapping
    public ResponseEntity<Partida> criar(@RequestBody Partida partida) {
        Partida salvo = partidaServico.salvar(partida);
        return ResponseEntity.ok(salvo);
    }

    @GetMapping
    public ResponseEntity<List<Partida>> listarTodas() {
        return ResponseEntity.ok(partidaServico.listarTodas());
    }
}