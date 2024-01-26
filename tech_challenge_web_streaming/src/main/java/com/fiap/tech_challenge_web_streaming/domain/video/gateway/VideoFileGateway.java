package com.fiap.tech_challenge_web_streaming.domain.video.gateway;

import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;

public interface VideoFileGateway {

    Mono<String> salvarArquivoVideo(FilePart videoFile);
}
