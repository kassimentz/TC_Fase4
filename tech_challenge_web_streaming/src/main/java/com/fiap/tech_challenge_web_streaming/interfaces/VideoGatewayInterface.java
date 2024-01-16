package com.fiap.tech_challenge_web_streaming.interfaces;

import com.fiap.tech_challenge_web_streaming.controllers.dto.usuario.UsuarioNovoResponseDTO;
import com.fiap.tech_challenge_web_streaming.controllers.dto.usuario.UsuarioRequestDTO;
import com.fiap.tech_challenge_web_streaming.controllers.dto.usuario.UsuarioResponseDTO;
import com.fiap.tech_challenge_web_streaming.entities.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Optional;


public interface VideoGatewayInterface {

    Flux<Page<Video>> getAllVideos(Pageable pageable, String titulo, LocalDate dataPublicacao, String categoria);

    Mono<Video> getVideoById(String id);

    Mono<Video> createVideo(Video video);

    Mono<Video> createVideo(Video video, MultipartFile file);

    Mono<Video> updateVideo(String id, Video video);

    Mono<Void> deleteVideo(String id);

//    Mono<VideoStatistics> getStatistics()


}
