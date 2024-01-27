package com.fiap.tech_challenge_web_streaming.usecase.video;

import com.fiap.tech_challenge_web_streaming.domain.video.gateway.VideoStreamGateway;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

public class VideoStreamUseCase {

    private VideoStreamGateway streamGateway;

    public VideoStreamUseCase(VideoStreamGateway streamGateway) {
        this.streamGateway = streamGateway;
    }

    public Mono<Void> streamVideoById(String id, ServerHttpResponse response) {
        return streamGateway.streamVideoById(id,response);
    }
}
