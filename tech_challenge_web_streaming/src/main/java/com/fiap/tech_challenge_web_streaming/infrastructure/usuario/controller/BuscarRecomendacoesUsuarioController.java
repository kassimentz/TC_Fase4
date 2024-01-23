package com.fiap.tech_challenge_web_streaming.infrastructure.usuario.controller;

import com.fiap.tech_challenge_web_streaming.domain.video.entity.Video;
import com.fiap.tech_challenge_web_streaming.infrastructure.video.dto.VideoPublicData;
import com.fiap.tech_challenge_web_streaming.usecase.usuario.BuscarRecomendacoesUseCase;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class BuscarRecomendacoesUsuarioController {

    private final BuscarRecomendacoesUseCase buscarRecomendacoesUseCase;

    public BuscarRecomendacoesUsuarioController(BuscarRecomendacoesUseCase buscarRecomendacoesUseCase) {
        this.buscarRecomendacoesUseCase = buscarRecomendacoesUseCase;
    }

    @GetMapping("/usuarios/{id}/recomendacoes")
    @Operation(summary = "Busca Recomendações de Vídeos")

    public ResponseEntity<Flux<VideoPublicData>> buscarRecomendacoes(@PathVariable String id) {
        Flux<Video> videosFlux =  buscarRecomendacoesUseCase.execute(id);
        Flux<VideoPublicData> responseData = videosFlux.map(VideoPublicData::new);
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseData);
    }
}
