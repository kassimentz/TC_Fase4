package com.fiap.tech_challenge_web_streaming.domain.video.gateway;

import com.fiap.tech_challenge_web_streaming.domain.categoria.entity.Categoria;
import com.fiap.tech_challenge_web_streaming.domain.video.entity.Video;
import com.fiap.tech_challenge_web_streaming.domain.video.factories.CriteriosBuscaVideo;
import com.fiap.tech_challenge_web_streaming.domain.video.factories.PageData;
import com.fiap.tech_challenge_web_streaming.infrastructure.video.entityschema.VideoEntity;
import com.fiap.tech_challenge_web_streaming.infrastructure.video.gateway.VideoDatabaseGateway;
import com.fiap.tech_challenge_web_streaming.infrastructure.video.repository.VideoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class VideoGatewayTest {

    @InjectMocks
    private VideoDatabaseGateway videoGateway;

    @Mock
    private VideoRepository videoRepository;

    @Mock
    private ReactiveMongoTemplate reactiveMongoTemplate;

    @BeforeEach
     void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
     void testBuscarTodosOsVideosPorAtributo(){
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

        when(reactiveMongoTemplate.find(query, VideoEntity.class)).thenReturn(videos.map(VideoEntity::new));

        Flux<Video> result = videoGateway.buscarTodosOsVideosPorAtributo(pageData, criteriosBuscaVideo);

        assertEquals(2, result.count().block());
        assertEquals("Test Video 1", result.blockFirst().getTitulo());
        assertEquals("Test Video 2", result.blockLast().getTitulo());
    }

    @Test
     void testBuscarTodos() {

        Video video1 = new Video("1", "Test Video 1", "This is a test video", "www.test.com", LocalDate.now(), Categoria.PETS);
        Video video2 = new Video("2", "Test Video 2", "This is another test video", "www.test.com", LocalDate.now(), Categoria.TECNOLOGIA);
        Flux<VideoEntity> videoEntities = Flux.just(new VideoEntity(video1), new VideoEntity(video2));

        when(videoRepository.findAll()).thenReturn(videoEntities);

        Flux<Video> result = videoGateway.buscarTodos();

        assertEquals(2, result.count().block());
        assertEquals("Test Video 1", result.blockFirst().getTitulo());
        assertEquals("Test Video 2", result.blockLast().getTitulo());
    }

    @Test
     void testBuscarPorId() {

        String id = "1";
        Video video = new Video("1", "Test Video 1", "This is a test video", "www.test.com", LocalDate.now(), Categoria.PETS);
        VideoEntity videoEntity = new VideoEntity(video);

        when(videoRepository.findById(id)).thenReturn(Mono.just(videoEntity));

        Mono<Video> result = videoGateway.buscarPorId(id);

        assertNotNull(result);
        assertEquals("Test Video 1", result.block().getTitulo());
    }

    @Test
     void testAtualizar() {

        Video video = new Video("1", "Test Video 1", "This is a test video", "www.test.com", LocalDate.now(), Categoria.PETS);
        VideoEntity videoEntity = new VideoEntity(video);

        when(videoRepository.save(videoEntity)).thenReturn(Mono.just(videoEntity));

        Mono<Video> result = videoGateway.atualizar(video);

        assertNotNull(result);
        assertEquals("Test Video 1", result.block().getTitulo());
    }

    @Test
     void testDeletar() {

        Video video = new Video("1", "Test Video 1", "This is a test video", "www.test.com", LocalDate.now(), Categoria.PETS);
        VideoEntity videoEntity = new VideoEntity(video);

        when(videoRepository.delete(videoEntity)).thenReturn(Mono.empty());

        Mono<Void> result = videoGateway.deletar(video);
        assertNull(result.block());
    }

    @Test
     void testCriar() {

        Video video = new Video("1", "Test Video 1", "This is a test video", "www.test.com", LocalDate.now(), Categoria.PETS);
        VideoEntity videoEntity = new VideoEntity(video);

        when(videoRepository.save(videoEntity)).thenReturn(Mono.just(videoEntity));
        Mono<Video> result = videoGateway.criar(video);
        assertNotNull(result);
        assertEquals("Test Video 1", result.block().getTitulo());
    }

    @Test
     void testCount() {

        Long expectedCount = 5L;

        when(videoRepository.count()).thenReturn(Mono.just(expectedCount));
        Mono<Long> result = videoGateway.count();
        assertNotNull(result);
        assertEquals(expectedCount, result.block());
    }

}
