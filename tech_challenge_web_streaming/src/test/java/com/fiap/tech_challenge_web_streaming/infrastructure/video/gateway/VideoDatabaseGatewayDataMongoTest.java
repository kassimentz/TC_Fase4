package com.fiap.tech_challenge_web_streaming.infrastructure.video.gateway;

import com.fiap.tech_challenge_web_streaming.domain.categoria.entity.Categoria;
import com.fiap.tech_challenge_web_streaming.domain.video.entity.Video;
import com.fiap.tech_challenge_web_streaming.domain.video.factories.CriteriosBuscaVideo;
import com.fiap.tech_challenge_web_streaming.domain.video.factories.PageData;
import com.fiap.tech_challenge_web_streaming.infrastructure.video.entityschema.VideoEntity;
import com.fiap.tech_challenge_web_streaming.infrastructure.video.repository.VideoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
 class VideoDatabaseGatewayDataMongoTest {

    @Autowired
    private VideoRepository repository;

    @Autowired
    private ReactiveMongoTemplate mongoTemplate;

    private VideoDatabaseGateway videoDatabaseGateway;

    @BeforeEach
     void setUp() {
        videoDatabaseGateway = new VideoDatabaseGateway(repository, mongoTemplate);

        for (int i = 1; i <= 5; i++) {
            VideoEntity videoEntity = new VideoEntity();
            videoEntity.setId(String.valueOf(i));
            mongoTemplate.save(videoEntity).block();
        }
    }

    @Test
     void testBuscarTodosOsVideosPorAtributo() {

        VideoEntity videoEntity = new VideoEntity();
        mongoTemplate.save(videoEntity);

        Video video1 = new Video("1", "Test Video 1", "This is a test video", "www.test.com", LocalDate.now(), Categoria.PETS);
        Video video2 = new Video("2", "Test Video 2", "This is another test video", "www.test.com", LocalDate.now(), Categoria.TECNOLOGIA);
        Flux<Video> videos = Flux.just(video1, video2);
        PageData pageData = PageData.newPageBuilder().page(0).size(10).direcao("ASC").ordenacao("titulo").build();
        CriteriosBuscaVideo criteriosBuscaVideo = CriteriosBuscaVideo.newBuilder().titulo("Test Video").build();

        Query query = new Query().with(PageRequest.of(
                pageData.getPage(), pageData.getSize(),
                Sort.Direction.valueOf(pageData.getDirecao()),
                pageData.getOrdenacao()
        ));

        if(criteriosBuscaVideo.getTitulo() != null){
            query.addCriteria(Criteria.where("titulo").regex(criteriosBuscaVideo.getTitulo(), "i"));
        }


        Flux<Video> result = videoDatabaseGateway.buscarTodosOsVideosPorAtributo(pageData, criteriosBuscaVideo);


        StepVerifier.create(result)
                .assertNext(video -> assertThat(video).isNotNull())
                .verifyComplete();
    }

    @Test
     void testBuscarTodos() {

        VideoEntity videoEntity = new VideoEntity();
        mongoTemplate.save(videoEntity);

        Flux<Video> result = videoDatabaseGateway.buscarTodos();

        StepVerifier.create(result)
                .assertNext(video -> assertThat(video).isNotNull())
                .verifyComplete();
    }

    @Test
     void testBuscarPorId() {

        VideoEntity videoEntity = new VideoEntity();
        videoEntity.setId("1");
        mongoTemplate.save(videoEntity);

        Mono<Video> result = videoDatabaseGateway.buscarPorId("1");

        StepVerifier.create(result)
                .assertNext(video -> assertThat(video).isNotNull())
                .verifyComplete();
    }

    @Test
     void testAtualizar() {

        VideoEntity videoEntity = new VideoEntity();
        videoEntity.setId("1");
        mongoTemplate.save(videoEntity);

        Video video = new Video();
        video.setId("1");

        Mono<Video> result = videoDatabaseGateway.atualizar(video);

        StepVerifier.create(result)
                .assertNext(updatedVideo -> assertThat(updatedVideo).isNotNull())
                .verifyComplete();
    }

    @Test
     void testDeletar() {

        VideoEntity videoEntity = new VideoEntity();
        videoEntity.setId("1");
        mongoTemplate.save(videoEntity);

        Video video = new Video();
        video.setId("1");

        Mono<Void> result = videoDatabaseGateway.deletar(video);

        StepVerifier.create(result)
                .verifyComplete();
    }

    @Test
     void testCriar() {

        Video video = new Video();
        video.setId("1");

        Mono<Video> result = videoDatabaseGateway.criar(video);

        StepVerifier.create(result)
                .assertNext(createdVideo -> assertThat(createdVideo).isNotNull())
                .verifyComplete();
    }

    @Test
     void testCount() {

        VideoEntity videoEntity = new VideoEntity();
        videoEntity.setId("1");
        mongoTemplate.save(videoEntity);

        Mono<Long> result = videoDatabaseGateway.count();


        StepVerifier.create(result)
                .assertNext(count -> assertThat(count).isEqualTo(5))
                .verifyComplete();
    }
}
