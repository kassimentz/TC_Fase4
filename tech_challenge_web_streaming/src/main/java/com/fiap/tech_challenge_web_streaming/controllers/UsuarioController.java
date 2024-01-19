package com.fiap.tech_challenge_web_streaming.controllers;

import com.fiap.tech_challenge_web_streaming.controllers.dto.usuario.UsuarioNovoResponseDTO;
import com.fiap.tech_challenge_web_streaming.controllers.dto.usuario.UsuarioRequestDTO;
import com.fiap.tech_challenge_web_streaming.controllers.dto.usuario.UsuarioResponseDTO;
import com.fiap.tech_challenge_web_streaming.entities.Video;
import com.fiap.tech_challenge_web_streaming.interfaces.UsuarioGatewayInterface;
import com.fiap.tech_challenge_web_streaming.usecases.UseCases;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioGatewayInterface usuarioGateway;

    @Autowired
    private UseCases usuarioUC;

    @PostMapping
    public Mono<ResponseEntity<UsuarioNovoResponseDTO>> createUser(@Valid @RequestBody UsuarioRequestDTO usuario) {
        Mono<UsuarioNovoResponseDTO> novoUsuario = usuarioGateway.novo(usuario);
        return novoUsuario.map(u -> new ResponseEntity<>(u, HttpStatus.CREATED));
    }

    @GetMapping
    @Operation(summary = "Busca todos usuarios")
    public ResponseEntity<Flux<UsuarioResponseDTO>> getAll() {
        Flux<UsuarioResponseDTO> usuarios = usuarioGateway.listar();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca usuario por ID")
    public Mono<ResponseEntity<Optional<UsuarioResponseDTO>>> userById(@PathVariable String id) {
        return usuarioGateway.listarPorId(id)
                .map(usuario -> new ResponseEntity<>(usuario, HttpStatus.OK))
                .switchIfEmpty(Mono.just(new ResponseEntity<>(HttpStatus.NOT_FOUND)));

    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza Usuario")
    public Mono<ResponseEntity<UsuarioResponseDTO>> atualizarUsuario(@PathVariable String id, @Valid @RequestBody UsuarioRequestDTO usuario) {
        Mono<UsuarioResponseDTO> usuarioAtualizado = usuarioGateway.atualiza(id, usuario);
        return usuarioAtualizado.map(u -> new ResponseEntity<>(u, HttpStatus.OK));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar usuario por Id")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        usuarioGateway.deletar(id).subscribe();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{id}/favoritos/{videoId}")
    @Operation(summary = "Adiciona video aos favoritos")
    public Mono<ResponseEntity<UsuarioResponseDTO>> addFavorito(@PathVariable String id, @PathVariable String videoId) {
        Mono<UsuarioResponseDTO> usuario = usuarioUC.addVideoFavorito(id, videoId);
        return usuario.map(u -> new ResponseEntity<>(u, HttpStatus.OK));
    }

    @GetMapping("/{id}/recomendacoes")
    public Flux<Video> getRecommendations(@PathVariable String id) {
        return usuarioUC.getRecomendacoes(id);
    }
}
