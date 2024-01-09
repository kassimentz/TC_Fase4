package com.fiap.tech_challenge_web_streaming.gateways;

import com.fiap.tech_challenge_web_streaming.entities.Usuario;
import com.fiap.tech_challenge_web_streaming.entities.Video;
import com.fiap.tech_challenge_web_streaming.interfaces.VideoGatewayInterface;
import com.fiap.tech_challenge_web_streaming.interfaces.VideoRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class VideoGateway implements VideoGatewayInterface {

    @Autowired
    private VideoRepositoryInterface videoRepository;

    @Override
    public Flux<Video> getAllVideos() {
        return videoRepository.findAll();
    }

    @Override
    public Mono<Video> getVideoById(String id) {
        return videoRepository.findById(id);
    }

    @Override
    public Mono<Video> createVideo(Video video) {
        return videoRepository.save(video);
    }

    @Override
    public Mono<Video> updateVideo(String id, Video video) {
        return videoRepository.findById(id)
                .flatMap(existingVideo -> {
                    existingVideo.setTitulo(video.getTitulo());
                    existingVideo.setDescricao(video.getDescricao());
                    existingVideo.setUrl(video.getUrl());
                    return videoRepository.save(existingVideo);
                });
    }

    @Override
    public Mono<Void> deleteVideo(String id) {
        Mono<Video> videoMono = videoRepository.findById(id);
        return videoMono.flatMap(videoRepository::delete);
    }

}
