package com.fiap.tech_challenge_web_streaming.usecase.video;

import com.fiap.tech_challenge_web_streaming.domain.video.entity.Video;
import com.fiap.tech_challenge_web_streaming.domain.video.factories.CriteriosBuscaVideo;
import com.fiap.tech_challenge_web_streaming.domain.video.factories.PageData;
import com.fiap.tech_challenge_web_streaming.domain.video.gateway.VideoGateway;
import reactor.core.publisher.Flux;

public class BuscarTodosOsVideosPorAtributoComPaginacaoUseCase {

    private final VideoGateway videoGateway;

    public BuscarTodosOsVideosPorAtributoComPaginacaoUseCase(VideoGateway videoGateway) {
        this.videoGateway = videoGateway;
    }

    public Flux<Video> execute(PageData paginaVideo, CriteriosBuscaVideo criterios) {
        return this.videoGateway.buscarTodosOsVideosPorAtributo(paginaVideo, criterios);

    }

}
