package com.fiap.tech_challenge_web_streaming.infrastructure.usuario.controller;

import com.fiap.tech_challenge_web_streaming.domain.usuario.entity.Usuario;
import com.fiap.tech_challenge_web_streaming.infrastructure.usuario.dto.UsuarioPublicData;
import com.fiap.tech_challenge_web_streaming.usecase.usuario.BuscarTodosUsuariosUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Tag(name = "Usuário", description = "Usuário API")
@RestController
public class BuscarTodosUsuariosController {

    private final BuscarTodosUsuariosUseCase buscarTodosUsuariosUseCase;

    public BuscarTodosUsuariosController(BuscarTodosUsuariosUseCase buscarTodosUsuariosUseCase) {
        this.buscarTodosUsuariosUseCase = buscarTodosUsuariosUseCase;
    }


    //talvez deva substituir esse map por um collectlist antes dele
    @GetMapping("/usuarios")
    @Operation(summary = "Buscar todos os usuários")
    public ResponseEntity<Flux<UsuarioPublicData>> buscarTodosUsuarios() {
        Flux<Usuario> usuarioFlux = buscarTodosUsuariosUseCase.execute();
        Flux<UsuarioPublicData> responseData = usuarioFlux.map(UsuarioPublicData::new);

        return ResponseEntity.status(HttpStatus.OK)
                .body(responseData);

    }

}
