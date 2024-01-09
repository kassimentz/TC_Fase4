package com.fiap.tech_challenge_web_streaming.controllers;

import com.fiap.tech_challenge_web_streaming.controllers.dto.usuario.UsuarioResponseDTO;
import com.fiap.tech_challenge_web_streaming.entities.Video;
import com.fiap.tech_challenge_web_streaming.interfaces.VideoGatewayInterface;
import com.fiap.tech_challenge_web_streaming.interfaces.VideoRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@RestController
@RequestMapping("/videos")
public class VideoController {

    @Autowired
    private VideoGatewayInterface videoRepository;


    @GetMapping
    public ResponseEntity<Flux<Video>> getAllVideos() {
        Flux<Video> response = videoRepository.getAllVideos();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Video>>  getVideoById(@PathVariable String id) {
        return videoRepository.getVideoById(id)
                .map(video -> new ResponseEntity<>(video, HttpStatus.OK))
                .switchIfEmpty(Mono.just(new ResponseEntity<>(HttpStatus.NOT_FOUND)));
    }

    @PostMapping
    public Mono<ResponseEntity<Video>>createVideo(@RequestBody Video video) {
        Mono<Video> response = videoRepository.createVideo(video);
        return response.map(u -> new ResponseEntity<>(u, HttpStatus.CREATED));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Video>> updateVideo(@PathVariable String id, @RequestBody Video video) {
        Mono<Video> response = videoRepository.updateVideo(id, video);
        return response.map(u -> new ResponseEntity<>(u, HttpStatus.OK));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVideo(@PathVariable String id) {
        videoRepository.deleteVideo(id).subscribe();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
