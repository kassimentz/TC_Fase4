package com.fiap.tech_challenge_web_streaming.infrastructure.video.controller;

import com.fiap.tech_challenge_web_streaming.infrastructure.video.dto.VideoPublicData;
import com.fiap.tech_challenge_web_streaming.usecase.video.BuscarTodosOsVideosUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
@Tag(name = "Vídeo", description = "Vídeo API")
@RestController
public class BuscarTodosOsVideosController {

    private final BuscarTodosOsVideosUseCase buscarTodosOsVideosUseCase;


    public BuscarTodosOsVideosController(BuscarTodosOsVideosUseCase buscarTodosOsVideosUseCase) {
        this.buscarTodosOsVideosUseCase = buscarTodosOsVideosUseCase;
    }

    @GetMapping("/videos")
    @Operation(summary = "Buscar Todos os Vídeos")
    public ResponseEntity<Flux<VideoPublicData>> getAllVideos (){
        Flux<VideoPublicData>  responseData = buscarTodosOsVideosUseCase.execute()
                .map(VideoPublicData::new);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

}
