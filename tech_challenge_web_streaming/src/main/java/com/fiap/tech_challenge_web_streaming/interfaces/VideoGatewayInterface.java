package com.fiap.tech_challenge_web_streaming.interfaces;

import com.fiap.tech_challenge_web_streaming.controllers.dto.usuario.UsuarioNovoResponseDTO;
import com.fiap.tech_challenge_web_streaming.controllers.dto.usuario.UsuarioRequestDTO;
import com.fiap.tech_challenge_web_streaming.controllers.dto.usuario.UsuarioResponseDTO;
import com.fiap.tech_challenge_web_streaming.entities.Video;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;


public interface VideoGatewayInterface {

    Flux<Video> getAllVideos();

    Mono<Video> getVideoById(String id);

    Mono<Video> createVideo(Video video);

    Mono<Video> updateVideo(String id, Video video);

    Mono<Void> deleteVideo(String id);


}
