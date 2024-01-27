package com.fiap.tech_challenge_web_streaming.infrastructure.usuario.controller;

import com.fiap.tech_challenge_web_streaming.usecase.usuario.DeletarUsuarioUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
@Tag(name = "Usuário", description = "Usuário API")
@RestController
public class DeletarUsuarioController {

    private final DeletarUsuarioUseCase deletarUsuarioUseCase;


    public DeletarUsuarioController(DeletarUsuarioUseCase deletarUsuarioUseCase) {
        this.deletarUsuarioUseCase = deletarUsuarioUseCase;
    }


    @DeleteMapping("/usuarios/{id}")
    @Operation(summary = "Deletar Usuario por ID")
    public Mono<ResponseEntity<Void>> deletarUsuarioPorId(@PathVariable String id) {
        return deletarUsuarioUseCase.execute(id)
                .map(r -> {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                });
    }

}
