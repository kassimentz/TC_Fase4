package com.fiap.tech_challenge_web_streaming.usecase.video;

import com.fiap.tech_challenge_web_streaming.domain.categoria.entity.Categoria;
import com.fiap.tech_challenge_web_streaming.domain.video.entity.Video;
import com.fiap.tech_challenge_web_streaming.domain.video.factories.PageData;
import com.fiap.tech_challenge_web_streaming.domain.video.factories.CriteriosBuscaVideo;
import com.fiap.tech_challenge_web_streaming.domain.video.gateway.VideoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class BuscarTodosOsVideosPorAtributoComPaginacaoUseCaseTest {

    @Mock
    private VideoGateway videoGateway;

    private BuscarTodosOsVideosPorAtributoComPaginacaoUseCase buscarTodosOsVideosPorAtributoComPaginacaoUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        buscarTodosOsVideosPorAtributoComPaginacaoUseCase = new BuscarTodosOsVideosPorAtributoComPaginacaoUseCase(videoGateway);
    }

    @Test
    void testExecute() {
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

        when(videoGateway.buscarTodosOsVideosPorAtributo(any(PageData.class), any(CriteriosBuscaVideo.class))).thenReturn(Flux.just(video1));

        Flux<Video> result = buscarTodosOsVideosPorAtributoComPaginacaoUseCase.execute(pageData, criteriosBuscaVideo);

        StepVerifier.create(result)
                .expectNext(video1)
                .verifyComplete();
    }
}
