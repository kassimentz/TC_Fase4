package com.fiap.tech_challenge_web_streaming.controllers;

import com.fiap.tech_challenge_web_streaming.entities.Video;
import com.fiap.tech_challenge_web_streaming.interfaces.VideoGatewayInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/videos")
public class VideoController {

    @Autowired
    private VideoGatewayInterface videoGatewayInterface;


    @GetMapping
    public ResponseEntity<Flux<Page<Video>>> getAllVideos(@RequestParam(value = "page", defaultValue = "0") int page,
                                                           @RequestParam(value = "size", defaultValue = "10") int size,
                                                           @RequestParam(value = "direcao", defaultValue = "DESC") String direcao,
                                                           @RequestParam(value = "ordenacao", defaultValue = "id") String ordenacao){
        Flux<Page<Video>> response = videoGatewayInterface.getAllVideos(PageRequest.of(page, size, Sort.Direction.valueOf(direcao), ordenacao));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Video>>  getVideoById(@PathVariable String id) {
        return videoGatewayInterface.getVideoById(id)
                .map(video -> new ResponseEntity<>(video, HttpStatus.OK))
                .switchIfEmpty(Mono.just(new ResponseEntity<>(HttpStatus.NOT_FOUND)));
    }

    @PostMapping
    public Mono<ResponseEntity<Video>>createVideo(@RequestBody Video video) {
        Mono<Video> response = videoGatewayInterface.createVideo(video);
        return response.map(u -> new ResponseEntity<>(u, HttpStatus.CREATED));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Video>> updateVideo(@PathVariable String id, @RequestBody Video video) {
        Mono<Video> response = videoGatewayInterface.updateVideo(id, video);
        return response.map(u -> new ResponseEntity<>(u, HttpStatus.OK));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVideo(@PathVariable String id) {
        videoGatewayInterface.deleteVideo(id).subscribe();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
