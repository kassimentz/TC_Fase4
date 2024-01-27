package com.fiap.tech_challenge_web_streaming.infrastructure.usuario.controller;

import com.fiap.tech_challenge_web_streaming.domain.usuario.entity.Usuario;
import com.fiap.tech_challenge_web_streaming.infrastructure.usuario.dto.UsuarioPublicData;
import com.fiap.tech_challenge_web_streaming.infrastructure.usuario.dto.UsuarioRequestData;
import com.fiap.tech_challenge_web_streaming.usecase.usuario.AtualizarUsuarioUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
@Tag(name = "Usuário", description = "Usuário API")
@RestController
public class AtualizarUsuarioController {

    private final AtualizarUsuarioUseCase atualizarUsuarioUserCase;


    public AtualizarUsuarioController(AtualizarUsuarioUseCase atualizarUsuarioUserCase) {
        this.atualizarUsuarioUserCase = atualizarUsuarioUserCase;
    }

    @PutMapping("/usuarios/{id}")
    @Operation(summary = "Atualizar Usuário")
    public Mono<ResponseEntity<UsuarioPublicData>> atualizarUsuario(@PathVariable String id, @Valid @RequestBody UsuarioRequestData usuario) {
        Mono<Usuario> usuarioAtualizado = atualizarUsuarioUserCase.execute(id, usuario);
        return usuarioAtualizado.map(u -> new ResponseEntity<>(new UsuarioPublicData(u), HttpStatus.OK));
    }


}
