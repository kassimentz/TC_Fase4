package com.fiap.tech_challenge_web_streaming.gateways;

import com.fiap.tech_challenge_web_streaming.entities.Usuario;
import com.fiap.tech_challenge_web_streaming.entities.Video;
import com.fiap.tech_challenge_web_streaming.interfaces.VideoGatewayInterface;
import com.fiap.tech_challenge_web_streaming.interfaces.VideoRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class VideoGateway implements VideoGatewayInterface {

    @Autowired
    private VideoRepositoryInterface videoRepository;
    @Autowired
    private ReactiveMongoTemplate reactiveMongoTemplate;

    @Override
    public Flux<Video> getAllVideos(Pageable pageable) {
        Query query = new Query().with(pageable);
        return reactiveMongoTemplate.find(query, Video.class);
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
