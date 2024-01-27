package com.fiap.tech_challenge_web_streaming.infrastructure.estatistica.controller;

import com.fiap.tech_challenge_web_streaming.infrastructure.estatistica.dto.EstatisticaPublicData;
import com.fiap.tech_challenge_web_streaming.usecase.estatistica.BuscaEstatisticaUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Tag(name = "Estatistica", description = "Estatistica API")
@RestController
public class BuscarEstatisticasController {

    private final BuscaEstatisticaUseCase buscaEstatisticaUseCase;

    public BuscarEstatisticasController(BuscaEstatisticaUseCase buscaEstatisticaUseCase) {
        this.buscaEstatisticaUseCase = buscaEstatisticaUseCase;
    }

    @GetMapping("/videos/estatisticas")
    public Mono<ResponseEntity<EstatisticaPublicData>> getEstatistica() {
        return buscaEstatisticaUseCase.execute()
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
