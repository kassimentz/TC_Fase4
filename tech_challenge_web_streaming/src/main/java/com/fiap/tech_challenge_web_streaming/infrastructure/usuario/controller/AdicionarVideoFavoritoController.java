package com.fiap.tech_challenge_web_streaming.infrastructure.usuario.controller;

import com.fiap.tech_challenge_web_streaming.domain.usuario.entity.Usuario;
import com.fiap.tech_challenge_web_streaming.infrastructure.usuario.dto.UsuarioAddFavoritoRequestData;
import com.fiap.tech_challenge_web_streaming.infrastructure.usuario.dto.UsuarioPublicData;
import com.fiap.tech_challenge_web_streaming.infrastructure.usuario.dto.UsuarioUpdateData;
import com.fiap.tech_challenge_web_streaming.usecase.usuario.AddFavoritoUseCase;
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
public class AdicionarVideoFavoritoController {


    private final AddFavoritoUseCase adicionarVideoFavoritoUseCase;


    public AdicionarVideoFavoritoController(AddFavoritoUseCase adicionarVideoFavoritoUseCase) {
        this.adicionarVideoFavoritoUseCase = adicionarVideoFavoritoUseCase;
    }

    @PostMapping("/usuarios/{id}/favoritos/")
    @Operation(summary = "Adiciona Vídeo aos Favoritos")
    public Mono<ResponseEntity<UsuarioPublicData>> adicionarVideoFavorito(@PathVariable String id, @Valid @RequestBody UsuarioAddFavoritoRequestData usuario) {
        Mono<Usuario> usuarioAtualizado = adicionarVideoFavoritoUseCase.execute(id, usuario);
        return usuarioAtualizado.map(u -> new ResponseEntity<>(new UsuarioPublicData(u), HttpStatus.OK));
    }
}
