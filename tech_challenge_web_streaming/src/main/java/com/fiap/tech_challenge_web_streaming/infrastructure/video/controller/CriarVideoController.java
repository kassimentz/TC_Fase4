package com.fiap.tech_challenge_web_streaming.infrastructure.video.controller;

import com.fiap.tech_challenge_web_streaming.infrastructure.video.dto.VideoPublicData;
import com.fiap.tech_challenge_web_streaming.usecase.video.CriarVideoUseCase;
import com.fiap.tech_challenge_web_streaming.usecase.video.dto.IVideoPublicData;
import com.fiap.tech_challenge_web_streaming.usecase.video.dto.IVideoRequestData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
@Tag(name = "Vídeo", description = "Vídeo API")
@RestController
public class CriarVideoController {

    private final CriarVideoUseCase criarVideoUseCase;


    public CriarVideoController(CriarVideoUseCase criarVideoUseCase) {
        this.criarVideoUseCase = criarVideoUseCase;
    }


    @PostMapping("/videos")
    @Operation(summary = "Criar Vídeo")
    public Mono<ResponseEntity<IVideoPublicData>> createVideo(@RequestBody IVideoRequestData videoData) {
        return this.criarVideoUseCase.execute(videoData)
                .map(v -> new ResponseEntity<>(new VideoPublicData(v), HttpStatus.CREATED)
                );
    }
}
