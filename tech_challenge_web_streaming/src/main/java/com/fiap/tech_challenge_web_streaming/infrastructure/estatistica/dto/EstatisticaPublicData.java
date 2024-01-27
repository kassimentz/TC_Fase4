package com.fiap.tech_challenge_web_streaming.infrastructure.estatistica.dto;

import com.fiap.tech_challenge_web_streaming.usecase.estatistica.dto.IEstatisticaPublicData;
import reactor.core.publisher.Mono;

public record EstatisticaPublicData(
        Long quantidadeVisualizacoes,
        Long quantidadeVideos,
       Double mediaVisualizacoes
) implements IEstatisticaPublicData {
}
