package com.fiap.tech_challenge_web_streaming.usecase.estatisticas;

import com.fiap.tech_challenge_web_streaming.domain.video.gateway.VideoGateway;
import com.fiap.tech_challenge_web_streaming.infrastructure.estatistica.dto.EstatisticaPublicData;
import reactor.core.publisher.Mono;

import java.util.Locale;

public class BuscaEstatisticaUseCase {

    private final VideoGateway videoGateway;

    public BuscaEstatisticaUseCase(VideoGateway videoGateway) {
        this.videoGateway = videoGateway;
    }

    public Mono<EstatisticaPublicData> execute() {
        return Mono.zip(
                videoGateway.buscarQuantidadeVisualizacoes(),
                videoGateway.count(),
                (quantidadeVisualizacoes, quantidadeVideos) -> {
                    double mediaVisualizacoes = (quantidadeVideos != 0) ?
                            (double) quantidadeVisualizacoes / quantidadeVideos : 0.0;
                    String formattedMedia = String.format(Locale.US, "%.2f", mediaVisualizacoes);
                    return new EstatisticaPublicData(quantidadeVisualizacoes, quantidadeVideos, Double.parseDouble(formattedMedia));
                }
        );
    }

}
