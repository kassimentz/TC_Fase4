package com.fiap.tech_challenge_web_streaming.infrastructure.usuario.controller;

import com.fiap.tech_challenge_web_streaming.domain.usuario.entity.Usuario;
import com.fiap.tech_challenge_web_streaming.infrastructure.usuario.dto.UsuarioPublicData;
import com.fiap.tech_challenge_web_streaming.usecase.usuario.BuscarUsuarioUseCase;
import com.fiap.tech_challenge_web_streaming.usecase.usuario.dto.IUsuarioPublicData;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class BuscarUsuarioController {

    private final BuscarUsuarioUseCase buscarUsuarioUseCase;


    public BuscarUsuarioController(BuscarUsuarioUseCase buscarUsuarioUseCase) {
        this.buscarUsuarioUseCase = buscarUsuarioUseCase;
    }


    @GetMapping("/usuarios/{id}")
    @Operation(summary = "Buscar usuário por ID")
    public Mono<ResponseEntity<IUsuarioPublicData>> buscarUsuarioporId(@PathVariable String id) {
        Mono<Usuario> userEntity = buscarUsuarioUseCase.execute(id);
        return userEntity.map(
                u -> {
                    return new ResponseEntity<>(new UsuarioPublicData(u), HttpStatus.OK);
                }
        );
    }
}
