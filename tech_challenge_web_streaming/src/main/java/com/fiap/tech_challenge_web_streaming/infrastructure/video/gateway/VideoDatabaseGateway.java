package com.fiap.tech_challenge_web_streaming.infrastructure.video.gateway;

import com.fiap.tech_challenge_web_streaming.domain.video.entity.Video;
import com.fiap.tech_challenge_web_streaming.domain.video.factories.CriteriosBuscaVideo;
import com.fiap.tech_challenge_web_streaming.domain.video.factories.PageData;
import com.fiap.tech_challenge_web_streaming.domain.video.gateway.VideoGateway;
import com.fiap.tech_challenge_web_streaming.infrastructure.video.entityschema.VideoEntity;
import com.fiap.tech_challenge_web_streaming.infrastructure.video.repository.VideoRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class VideoDatabaseGateway implements VideoGateway {

    private final VideoRepository repository;
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public VideoDatabaseGateway(VideoRepository repository, ReactiveMongoTemplate reactiveMongoTemplate) {
        this.repository = repository;
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }


    @Override
    public Flux<Video> buscarTodosOsVideosPorAtributo(PageData dadosPaginacao, CriteriosBuscaVideo criteriosBuscaVideo) {

        Pageable page = PageRequest.of(
                dadosPaginacao.getPage(), dadosPaginacao.getSize(),
                Sort.Direction.valueOf(dadosPaginacao.getDirecao()),
                dadosPaginacao.getOrdenacao()
        );

        Query query = new Query().with(page);

        if (criteriosBuscaVideo.getTitulo() != null) {
            query.addCriteria(Criteria.where("titulo").regex(criteriosBuscaVideo.getTitulo(), "i"));
        }

        if (criteriosBuscaVideo.getDataPublicacao() != null) {
            query.addCriteria(Criteria.where("dataPublicacao").is(criteriosBuscaVideo.getDataPublicacao()));
        }

        if (criteriosBuscaVideo.getCategoria() != null) {
            query.addCriteria(Criteria.where("categoria").regex(criteriosBuscaVideo.getCategoria(), "i"));
        }

        return reactiveMongoTemplate.find(query, VideoEntity.class).map(VideoEntity::toVideo);



    }

    @Override
    public Flux<Video> buscarTodos() {
        Flux<VideoEntity> videoEntityFlux = this.repository.findAll();
        return videoEntityFlux.map(VideoEntity::toVideo);
    }

    @Override
    public Mono<Video> buscarPorId(String id) {
        Mono<VideoEntity> videoEntityMono = this.repository.findById(id);
        return videoEntityMono.map(VideoEntity::toVideo);
    }

    @Override
    public Mono<Video> atualizar(Video video) {
        VideoEntity videoEntity = new VideoEntity(video);
        return this.repository.save(videoEntity).map(VideoEntity::toVideo);
    }

    @Override
    public Mono<Void> deletar(Video video) {
        VideoEntity videoEntity = new VideoEntity(video);
        return this.repository.delete(videoEntity);
    }

    @Override
    public Mono<Video> criar(Video video) {

        VideoEntity videoEntity = new VideoEntity(video);
        return this.repository.save(videoEntity).
                map(VideoEntity::toVideo);

    }

    @Override
    public Mono<Long> count() {
        return this.repository.count();
    }
}
