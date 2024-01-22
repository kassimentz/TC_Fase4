package com.fiap.tech_challenge_web_streaming.infrastructure.video.controller;

import com.fiap.tech_challenge_web_streaming.usecase.video.DeletarVideoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class DeletarVideoController {

    private final DeletarVideoUseCase deletarVideoUseCase;


    public DeletarVideoController(DeletarVideoUseCase deletarVideoUseCase) {
        this.deletarVideoUseCase = deletarVideoUseCase;
    }


    @DeleteMapping("/videos/{id}")
    public Mono<ResponseEntity<Void>> deletarVideo(@PathVariable String id) {
        return this.deletarVideoUseCase.execute(id).map(v -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

}
