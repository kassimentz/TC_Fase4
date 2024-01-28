package com.fiap.tech_challenge_web_streaming.infrastructure.video.controller;

import com.fiap.tech_challenge_web_streaming.infrastructure.video.dto.VideoPublicData;
import com.fiap.tech_challenge_web_streaming.infrastructure.video.dto.VideoUpdateData;
import com.fiap.tech_challenge_web_streaming.usecase.video.AtualizarVideoUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Tag(name = "Vídeo", description = "Vídeo API")
@RestController
public class AtualizarVideoController {

    private final AtualizarVideoUseCase atualizarVideoUseCase;

    public AtualizarVideoController(AtualizarVideoUseCase atualizarVideoUseCase) {
        this.atualizarVideoUseCase = atualizarVideoUseCase;
    }

    @PutMapping("/videos/{id}")
    @Operation(summary = "Atualizar Vídeo")
    public Mono<ResponseEntity<VideoPublicData>> atualizarVideo(@Valid @RequestBody VideoUpdateData dados, @PathVariable String id) {
        return this.atualizarVideoUseCase.execute(id, dados)
                .map(video -> new ResponseEntity(new VideoPublicData(video), HttpStatus.OK));
    }

}
