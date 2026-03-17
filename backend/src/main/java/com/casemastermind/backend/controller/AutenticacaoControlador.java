package com.mastermind.controller;

import com.mastermind.model.Usuario;
import com.mastermind.service.UsuarioServico;
import com.mastermind.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticacaoControlador {
    @Autowired
    private UsuarioServico usuarioServico;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<?> autenticar(@RequestBody Usuario usuario) {
        return usuarioServico.buscarPorEmail(usuario.getEmail())
                .filter(u -> u.getSenha().equals(usuario.getSenha()))
                .map(u -> ResponseEntity.ok(jwtUtil.gerarToken(u.getEmail())))
                .orElse(ResponseEntity.status(401).body("Credenciais inválidas"));
    }
}