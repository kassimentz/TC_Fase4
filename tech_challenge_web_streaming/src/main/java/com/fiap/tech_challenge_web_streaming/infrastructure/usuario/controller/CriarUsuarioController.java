package com.fiap.tech_challenge_web_streaming.infrastructure.usuario.controller;

import com.fiap.tech_challenge_web_streaming.domain.usuario.entity.Usuario;
import com.fiap.tech_challenge_web_streaming.infrastructure.usuario.dto.UsuarioPublicData;
import com.fiap.tech_challenge_web_streaming.infrastructure.usuario.dto.UsuarioRequestData;
import com.fiap.tech_challenge_web_streaming.usecase.usuario.CriarUsuarioUseCase;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class CriarUsuarioController {

    private final CriarUsuarioUseCase criarUsuarioUseCase;


    public CriarUsuarioController(CriarUsuarioUseCase criarUsuarioUseCase) {
        this.criarUsuarioUseCase = criarUsuarioUseCase;
    }

    @PostMapping("/usuarios")
    @Operation(summary = "Novo Usuário")
    public Mono<ResponseEntity<UsuarioPublicData>> criarUsuario(@Valid @RequestBody UsuarioRequestData usuario) {
        Mono<Usuario> usuarioCriado = criarUsuarioUseCase.executar(usuario);
        return usuarioCriado.map(u -> new ResponseEntity<>(new UsuarioPublicData(u), HttpStatus.CREATED));
    }



}
