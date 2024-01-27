package com.fiap.tech_challenge_web_streaming.infrastructure.video.controller;

import com.fiap.tech_challenge_web_streaming.usecase.video.VideoStreamUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Tag(name = "Vídeo", description = "Vídeo API")
@RestController
public class VideoStreamController {

    private final VideoStreamUseCase videoStreamUseCase;

    public VideoStreamController(VideoStreamUseCase videoStreamUseCase) {
        this.videoStreamUseCase = videoStreamUseCase;
    }

    @GetMapping("/stream/{id}")
    public Mono<Void> streamVideo(ServerHttpResponse response, @PathVariable String id) {
        return videoStreamUseCase.streamVideoById(id, response);
    }
}
