package com.fiap.tech_challenge_web_streaming.controllers;

import com.fiap.tech_challenge_web_streaming.controllers.dto.UsuarioRequestDTO;
import com.fiap.tech_challenge_web_streaming.controllers.dto.UsuarioResponseDTO;
import com.fiap.tech_challenge_web_streaming.interfaces.UsuarioGatewayInterface;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioGatewayInterface usuarioGateway;

    @PostMapping
    @Operation(summary = "Novo Usuário")
    public ResponseEntity<UsuarioResponseDTO> createUser(@Valid @RequestBody UsuarioRequestDTO usuario) {
        Mono<UsuarioResponseDTO> novoUsuario = usuarioGateway.novo(usuario);
        return new ResponseEntity<>(novoUsuario.block(), HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Busca todos usuarios")
    public ResponseEntity<Flux<UsuarioResponseDTO>> getAll() {
        Flux<UsuarioResponseDTO> usuarios = usuarioGateway.listar();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca usuario por ID")
    public Mono<ResponseEntity<Optional<UsuarioResponseDTO>>> userById(@PathVariable Long id) {
        return usuarioGateway.listarPorId(id)
                .map(usuario -> new ResponseEntity<>(usuario, HttpStatus.OK))
                .switchIfEmpty(Mono.just(new ResponseEntity<>(HttpStatus.NOT_FOUND)));

    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza Usuario")
    public ResponseEntity<UsuarioResponseDTO> atualizarUsuario(@PathVariable Long id, @Valid @RequestBody UsuarioRequestDTO usuario) {
        Mono<UsuarioResponseDTO> usuarioAtualizado = usuarioGateway.atualiza(id, usuario);
        return new ResponseEntity<>(usuarioAtualizado.block(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar usuario por Id")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        usuarioGateway.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
