package com.fiap.tech_challenge_web_streaming.infrastructure.video.controller;

import com.fiap.tech_challenge_web_streaming.domain.video.entity.Video;
import com.fiap.tech_challenge_web_streaming.infrastructure.video.dto.VideoPublicData;
import com.fiap.tech_challenge_web_streaming.usecase.video.BuscarTodosOsVideosUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class BuscarTodosOsVideosController {

    private final BuscarTodosOsVideosUseCase buscarTodosOsVideosUseCase;


    public BuscarTodosOsVideosController(BuscarTodosOsVideosUseCase buscarTodosOsVideosUseCase) {
        this.buscarTodosOsVideosUseCase = buscarTodosOsVideosUseCase;
    }

    @GetMapping("/videos")
    public Flux<ResponseEntity<VideoPublicData>> getAllVideos (){
        return buscarTodosOsVideosUseCase.execute()
                .map(v -> new ResponseEntity<>(new VideoPublicData(v), HttpStatus.OK));
    }



}
