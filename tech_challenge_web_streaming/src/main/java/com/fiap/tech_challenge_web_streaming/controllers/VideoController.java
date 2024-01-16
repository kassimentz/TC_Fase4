package com.fiap.tech_challenge_web_streaming.controllers;

import com.fiap.tech_challenge_web_streaming.entities.Video;
import com.fiap.tech_challenge_web_streaming.interfaces.VideoGatewayInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@RestController
@RequestMapping("/videos")
public class VideoController {

    @Autowired
    private VideoGatewayInterface videoGatewayInterface;


    @GetMapping
    public ResponseEntity<Flux<Page<Video>>> getAllVideos(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "direcao", defaultValue = "DESC") String direcao,
            @RequestParam(value = "ordenacao", defaultValue = "id") String ordenacao,
            @RequestParam(value = "titulo", required = false) String titulo,
            @RequestParam(value = "dataPublicacao", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataPublicacao,
            @RequestParam(value = "categoria", required = false) String categoria) {

        Flux<Page<Video>> response = videoGatewayInterface.getAllVideos(
                PageRequest.of(page, size, Sort.Direction.valueOf(direcao), ordenacao),
                titulo,
                dataPublicacao,
                categoria);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public Mono<ResponseEntity<Video>>  getVideoById(@PathVariable String id) {
        return videoGatewayInterface.getVideoById(id)
                .map(video -> new ResponseEntity<>(video, HttpStatus.OK))
                .switchIfEmpty(Mono.just(new ResponseEntity<>(HttpStatus.NOT_FOUND)));
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Mono<ResponseEntity<Video>> createVideo(@RequestPart("video") MultipartFile file, @RequestPart("videoDetails") Mono<Video> video) {
        return video
                .flatMap(videoDetails -> videoGatewayInterface.createVideo(videoDetails, file))
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
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

//    @GetMapping("/statistics")
//    public Mono<ResponseEntity<VideoStatistics>> getStatistics() {
//        return videoService.getStatistics()
//                .map(ResponseEntity::ok)
//                .defaultIfEmpty(ResponseEntity.notFound().build());
//    }

}
