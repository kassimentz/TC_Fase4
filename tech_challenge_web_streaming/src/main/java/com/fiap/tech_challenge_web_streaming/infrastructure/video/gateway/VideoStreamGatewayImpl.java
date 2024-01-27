package com.fiap.tech_challenge_web_streaming.infrastructure.video.gateway;

import com.fiap.tech_challenge_web_streaming.domain.video.exception.VideoNaoEncontradoException;
import com.fiap.tech_challenge_web_streaming.domain.video.gateway.VideoStreamGateway;
import com.fiap.tech_challenge_web_streaming.infrastructure.video.repository.VideoRepository;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.file.Path;
import java.nio.file.Paths;

public class VideoStreamGatewayImpl implements VideoStreamGateway {

    private final VideoRepository repository;

    public VideoStreamGatewayImpl(VideoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Void> streamVideoById(String id, ServerHttpResponse response) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(VideoNaoEncontradoException::new))
                .flatMap(video -> {
                    Path path = Paths.get(video.getUrl());
                    FileSystemResource videoFile = new FileSystemResource(path);
                    Flux<DataBuffer> dataBufferFlux = DataBufferUtils.read(videoFile, response.bufferFactory(), 4096);
                    return response.writeWith(dataBufferFlux);
                });
    }
}
