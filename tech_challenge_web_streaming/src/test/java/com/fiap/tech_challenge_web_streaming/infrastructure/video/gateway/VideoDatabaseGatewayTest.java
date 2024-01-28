package com.fiap.tech_challenge_web_streaming.infrastructure.video.gateway;

import com.fiap.tech_challenge_web_streaming.domain.categoria.entity.Categoria;
import com.fiap.tech_challenge_web_streaming.domain.video.entity.Video;
import com.fiap.tech_challenge_web_streaming.domain.video.factories.CriteriosBuscaVideo;
import com.fiap.tech_challenge_web_streaming.domain.video.factories.PageData;
import com.fiap.tech_challenge_web_streaming.infrastructure.video.entityschema.VideoEntity;
import com.fiap.tech_challenge_web_streaming.infrastructure.video.repository.VideoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

 class VideoDatabaseGatewayTest {

    @Mock
    private VideoRepository repository;

    @Mock
    private ReactiveMongoTemplate reactiveMongoTemplate;

    private VideoDatabaseGateway videoDatabaseGateway;

    @BeforeEach
     void setUp() {
        MockitoAnnotations.openMocks(this);
        videoDatabaseGateway = new VideoDatabaseGateway(repository, reactiveMongoTemplate);
    }

    @Test
     void testBuscarTodosOsVideosPorAtributo() {

        Video video1 = new Video("1", "Test Video 1", "This is a test video", "www.test.com", Categoria.PETS);
        Video video2 = new Video("2", "Test Video 2", "This is another test video", "www.test.com", Categoria.TECNOLOGIA);
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

        when(reactiveMongoTemplate.find(any(), eq(VideoEntity.class))).thenReturn(Flux.just(new VideoEntity()));

        Flux<Video> result = videoDatabaseGateway.buscarTodosOsVideosPorAtributo(pageData, criteriosBuscaVideo);

        StepVerifier.create(result)
                .expectNextCount(1)
                .verifyComplete();

        verify(reactiveMongoTemplate, times(1)).find(any(), eq(VideoEntity.class));
    }

    @Test
     void testBuscarTodos() {
        when(repository.findAll()).thenReturn(Flux.just(new VideoEntity()));

        Flux<Video> result = videoDatabaseGateway.buscarTodos();

        StepVerifier.create(result)
                .expectNextCount(1)
                .verifyComplete();

        verify(repository, times(1)).findAll();
    }

    @Test
     void testBuscarPorId() {
        when(repository.findById(anyString())).thenReturn(Mono.just(new VideoEntity()));

        Mono<Video> result = videoDatabaseGateway.buscarPorId("1");

        StepVerifier.create(result)
                .expectNextCount(1)
                .verifyComplete();

        verify(repository, times(1)).findById(anyString());
    }

    @Test
     void testAtualizar() {
        when(repository.save(any(VideoEntity.class))).thenReturn(Mono.just(new VideoEntity()));

        Mono<Video> result = videoDatabaseGateway.atualizar(new Video());

        StepVerifier.create(result)
                .expectNextCount(1)
                .verifyComplete();

        verify(repository, times(1)).save(any(VideoEntity.class));
    }

    @Test
     void testDeletar() {
        when(repository.delete(any(VideoEntity.class))).thenReturn(Mono.empty());

        Mono<Void> result = videoDatabaseGateway.deletar(new Video());

        StepVerifier.create(result)
                .verifyComplete();

        verify(repository, times(1)).delete(any(VideoEntity.class));
    }

    @Test
     void testCriar() {
        when(repository.save(any(VideoEntity.class))).thenReturn(Mono.just(new VideoEntity()));

        Mono<Video> result = videoDatabaseGateway.criar(new Video());

        StepVerifier.create(result)
                .expectNextCount(1)
                .verifyComplete();

        verify(repository, times(1)).save(any(VideoEntity.class));
    }

    @Test
     void testCount() {
        when(repository.count()).thenReturn(Mono.just(1L));

        Mono<Long> result = videoDatabaseGateway.count();

        StepVerifier.create(result)
                .expectNext(1L)
                .verifyComplete();

        verify(repository, times(1)).count();
    }
}
