package com.fiap.tech_challenge_web_streaming.usecase.video;

import com.fiap.tech_challenge_web_streaming.domain.video.exception.VideoNaoEncontradoException;
import com.fiap.tech_challenge_web_streaming.domain.video.gateway.VideoGateway;
import reactor.core.publisher.Mono;

public class DeletarVideoUseCase {

    private final VideoGateway videoGateway;

    public DeletarVideoUseCase(VideoGateway videoGateway) {
        this.videoGateway = videoGateway;
    }

    public Mono<Void> execute(String id) throws VideoNaoEncontradoException {
        return videoGateway.buscarPorId(id)
                .switchIfEmpty(Mono.error(new VideoNaoEncontradoException()))
                .flatMap(video -> videoGateway.deletar(video));
    }
}
