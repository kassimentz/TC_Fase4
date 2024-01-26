package com.fiap.tech_challenge_web_streaming.usecase.video;

import com.fiap.tech_challenge_web_streaming.domain.video.entity.Video;
import com.fiap.tech_challenge_web_streaming.domain.video.gateway.VideoGateway;
import reactor.core.publisher.Flux;

public class BuscarTodosOsVideosUseCase {

    private final VideoGateway videoGateway;


    public BuscarTodosOsVideosUseCase(VideoGateway videoGateway) {
        this.videoGateway = videoGateway;
    }

    public Flux<Video> execute( ) {
        return this.videoGateway.buscarTodos();
    }


}
