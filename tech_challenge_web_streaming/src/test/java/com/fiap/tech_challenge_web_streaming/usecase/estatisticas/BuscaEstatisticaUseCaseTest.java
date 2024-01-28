package com.fiap.tech_challenge_web_streaming.usecase.estatisticas;

import com.fiap.tech_challenge_web_streaming.domain.video.gateway.VideoGateway;
import com.fiap.tech_challenge_web_streaming.infrastructure.estatistica.dto.EstatisticaPublicData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BuscaEstatisticaUseCaseTest {

    private VideoGateway videoGateway;
    private BuscaEstatisticaUseCase buscaEstatisticaUseCase;

    @BeforeEach
    public void setUp() {
        videoGateway = mock(VideoGateway.class);
        buscaEstatisticaUseCase = new BuscaEstatisticaUseCase(videoGateway);
    }

    @Test
    public void testExecute() {
        Long quantidadeVisualizacoes = 100L;
        Long quantidadeVideos = 10L;
        Double mediaVisualizacoes = 10.0;

        when(videoGateway.buscarQuantidadeVisualizacoes()).thenReturn(Mono.just(quantidadeVisualizacoes));
        when(videoGateway.count()).thenReturn(Mono.just(quantidadeVideos));

        Mono<EstatisticaPublicData> result = buscaEstatisticaUseCase.execute();

        StepVerifier.create(result)
                .expectNextMatches(estatisticaPublicData -> estatisticaPublicData.quantidadeVisualizacoes().equals(quantidadeVisualizacoes) &&
                        estatisticaPublicData.quantidadeVideos().equals(quantidadeVideos) &&
                        estatisticaPublicData.mediaVisualizacoes().equals(mediaVisualizacoes))
                .verifyComplete();
    }
}
