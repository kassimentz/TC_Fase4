package com.fiap.tech_challenge_web_streaming.domain.video.gateway;

import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

public interface VideoStreamGateway {
    Mono<Void> streamVideoById(String id, ServerHttpResponse response);
}
