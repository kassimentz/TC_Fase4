package com.fiap.tech_challenge_web_streaming.usecase.video;

import com.fiap.tech_challenge_web_streaming.domain.video.entity.Video;
import com.fiap.tech_challenge_web_streaming.domain.video.exception.VideoNaoEncontradoException;
import com.fiap.tech_challenge_web_streaming.domain.video.gateway.VideoGateway;
import reactor.core.publisher.Mono;

public class BuscarVideoUseCase {

    private final VideoGateway videoGateway;

    public BuscarVideoUseCase(VideoGateway videoGateway) {
        this.videoGateway = videoGateway;
    }

    public Mono<Video> execute(String id) throws VideoNaoEncontradoException {
        return videoGateway.buscarPorId(id)
                .flatMap(video -> {
                    video.setQtVisualizacao(video.getQtVisualizacao() + 1L);
                    return videoGateway.atualizar(video);
                })
                .switchIfEmpty(Mono.error(VideoNaoEncontradoException::new));
    }
}