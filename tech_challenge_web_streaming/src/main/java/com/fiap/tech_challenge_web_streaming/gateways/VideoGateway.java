package com.fiap.tech_challenge_web_streaming.gateways;

import com.fiap.tech_challenge_web_streaming.entities.Categoria;
import com.fiap.tech_challenge_web_streaming.entities.Usuario;
import com.fiap.tech_challenge_web_streaming.entities.Video;
import com.fiap.tech_challenge_web_streaming.interfaces.VideoGatewayInterface;
import com.fiap.tech_challenge_web_streaming.interfaces.VideoRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

@Service
public class VideoGateway implements VideoGatewayInterface {

    @Autowired
    private VideoRepositoryInterface videoRepository;
    @Autowired
    private ReactiveMongoTemplate reactiveMongoTemplate;

    @Override
    public Flux<Page<Video>> getAllVideos(Pageable pageable, String titulo, LocalDate dataPublicacao, String categoria) {
        Query query = new Query().with(pageable);

        if (titulo != null) {
            query.addCriteria(Criteria.where("titulo").regex(titulo, "i"));
        }

        if (dataPublicacao != null) {
            query.addCriteria(Criteria.where("dataPublicacao").is(dataPublicacao));
        }

        if (categoria != null) {
            query.addCriteria(Criteria.where("categorias").in(categoria));
        }


        return reactiveMongoTemplate.find(query, Video.class)
                .collectList()
                .map(videoList -> new PageImpl<>(videoList, pageable, videoList.size()))
                .flatMapMany(Flux::just);
    }


    @Override
    public Mono<Video> getVideoById(String id) {
        return videoRepository.findById(id)
                .flatMap(video -> {
                    video.setQtVisualizacao(video.getQtVisualizacao() + 1L);
                    return videoRepository.save(video);
                });
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
                    existingVideo.setQtVisualizacao(0L);
                    return videoRepository.save(existingVideo);
                });
    }

    @Override
    public Mono<Void> deleteVideo(String id) {
        Mono<Video> videoMono = videoRepository.findById(id);
        return videoMono.flatMap(videoRepository::delete);
    }

}
