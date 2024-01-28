package com.fiap.tech_challenge_web_streaming.infrastructure.estatistica.controller;

import com.fiap.tech_challenge_web_streaming.infrastructure.estatistica.dto.EstatisticaPublicData;
import com.fiap.tech_challenge_web_streaming.usecase.estatisticas.BuscaEstatisticaUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BuscarEstatisticasControllerTest {

    private BuscaEstatisticaUseCase buscaEstatisticaUseCase;
    private BuscarEstatisticasController buscarEstatisticasController;

    @BeforeEach
    public void setUp() {
        buscaEstatisticaUseCase = mock(BuscaEstatisticaUseCase.class);
        buscarEstatisticasController = new BuscarEstatisticasController(buscaEstatisticaUseCase);
    }

    @Test
    public void testGetEstatistica() {
        EstatisticaPublicData estatisticaPublicData = new EstatisticaPublicData(100L, 10L, 10.0);
        when(buscaEstatisticaUseCase.execute()).thenReturn(Mono.just(estatisticaPublicData));

        Mono<ResponseEntity<EstatisticaPublicData>> result = buscarEstatisticasController.getEstatistica();

        StepVerifier.create(result)
                .expectNextMatches(response -> response.getStatusCode().is2xxSuccessful() &&
                        response.getBody().equals(estatisticaPublicData))
                .verifyComplete();
    }
}
