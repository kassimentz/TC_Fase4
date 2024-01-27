package com.fiap.tech_challenge_web_streaming.infrastructure.video.controller;

import com.fiap.tech_challenge_web_streaming.domain.categoria.entity.Categoria;
import com.fiap.tech_challenge_web_streaming.domain.video.entity.Video;
import com.fiap.tech_challenge_web_streaming.infrastructure.video.dto.VideoPublicData;
import com.fiap.tech_challenge_web_streaming.usecase.video.BuscarTodosOsVideosUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.LocalDate;

import static org.mockito.Mockito.when;

class BuscarTodosOsVideosControllerTest {

    @Mock
    private BuscarTodosOsVideosUseCase buscarTodosOsVideosUseCase;

    private BuscarTodosOsVideosController controller;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        controller = new BuscarTodosOsVideosController(buscarTodosOsVideosUseCase);
    }

    @Test
    void testGetAllVideos() {
        Video video = new Video("Titulo", "Descricao", Categoria.PETS, "url");
        VideoPublicData videoPublicData = new VideoPublicData(video);
        when(buscarTodosOsVideosUseCase.execute()).thenReturn(Flux.just(video));

        ResponseEntity<Flux<VideoPublicData>> result = controller.getAllVideos();

       StepVerifier.create(result.getBody())
                    .expectNext(videoPublicData)
                    .verifyComplete();
    }
}
