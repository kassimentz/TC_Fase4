package com.fiap.tech_challenge_web_streaming.infrastructure.video.controller;

import com.fiap.tech_challenge_web_streaming.infrastructure.video.dto.VideoPublicData;
import com.fiap.tech_challenge_web_streaming.usecase.video.AtualizarVideoUseCase;
import com.fiap.tech_challenge_web_streaming.usecase.video.dto.IVideoPublicData;
import com.fiap.tech_challenge_web_streaming.usecase.video.dto.IVideoUpdateData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class AtualizarVideoController {

    private final AtualizarVideoUseCase atualizarVideoUseCase;

    public AtualizarVideoController(AtualizarVideoUseCase atualizarVideoUseCase) {
        this.atualizarVideoUseCase = atualizarVideoUseCase;
    }

    @PutMapping("/videos/{id}")
    public Mono<ResponseEntity<IVideoPublicData>> atualizarVideo(@RequestBody IVideoUpdateData dados, @PathVariable String id) {
        return this.atualizarVideoUseCase.execute(id, dados)
                .map(video -> new ResponseEntity(new VideoPublicData(video), HttpStatus.OK));


    }



}
