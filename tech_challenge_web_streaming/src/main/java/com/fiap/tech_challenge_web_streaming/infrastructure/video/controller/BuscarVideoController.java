package com.fiap.tech_challenge_web_streaming.infrastructure.video.controller;

import com.fiap.tech_challenge_web_streaming.domain.video.entity.Video;
import com.fiap.tech_challenge_web_streaming.infrastructure.video.dto.VideoPublicData;
import com.fiap.tech_challenge_web_streaming.usecase.video.BuscarVideoUseCase;
import com.fiap.tech_challenge_web_streaming.usecase.video.dto.IVideoPublicData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
@Tag(name = "Vídeo", description = "Vídeo API")
@RestController
public class BuscarVideoController {

    private final BuscarVideoUseCase buscarVideoUseCase;


    public BuscarVideoController(BuscarVideoUseCase buscarVideoUseCase) {
        this.buscarVideoUseCase = buscarVideoUseCase;
    }

    @GetMapping("/videos/{id}")
    @Operation(summary = "Buscar Vídeo por ID")
    public Mono<ResponseEntity<VideoPublicData>> getVideoById(@PathVariable String id) {
        Mono<Video> videoEncontrado = buscarVideoUseCase.execute(id);
        return videoEncontrado.map(v -> new ResponseEntity(new VideoPublicData(v), HttpStatus.OK));
    }

}
