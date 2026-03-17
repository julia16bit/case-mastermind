package com.mastermind.controller;

import com.mastermind.model.Usuario;
import com.mastermind.model.UsuarioDTO;
import com.mastermind.service.UsuarioServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioControlador {
    @Autowired
    private UsuarioServico usuarioServico;

    @PostMapping
    public ResponseEntity<Usuario> criar(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(usuarioDTO.getSenha());
        Usuario salvo = usuarioServico.salvar(usuario);
        return ResponseEntity.ok(salvo);
    }

    @GetMapping("/{email}")
    public ResponseEntity<Usuario> buscarPorEmail(@PathVariable String email) {
        Optional<Usuario> usuario = usuarioServico.buscarPorEmail(email);
        return usuario.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> ranking() {
        List<Usuario> ranking = usuarioServico.rankingPorPontuacao();
        return ResponseEntity.ok(ranking);
    }
}
