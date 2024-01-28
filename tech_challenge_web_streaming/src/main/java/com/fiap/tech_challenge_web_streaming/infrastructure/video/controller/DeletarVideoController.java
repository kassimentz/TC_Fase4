package com.fiap.tech_challenge_web_streaming.infrastructure.video.controller;

import com.fiap.tech_challenge_web_streaming.usecase.video.DeletarVideoUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
@Tag(name = "Vídeo", description = "Vídeo API")
@RestController
public class DeletarVideoController {

    private final DeletarVideoUseCase deletarVideoUseCase;


    public DeletarVideoController(DeletarVideoUseCase deletarVideoUseCase) {
        this.deletarVideoUseCase = deletarVideoUseCase;
    }


    @DeleteMapping("/videos/{id}")
    @Operation(summary = "Deletar Vídeo por ID")
    public Mono<ResponseEntity<Void>> deletarVideo(@PathVariable String id) {
        return deletarVideoUseCase.execute(id)
                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
    }

}
