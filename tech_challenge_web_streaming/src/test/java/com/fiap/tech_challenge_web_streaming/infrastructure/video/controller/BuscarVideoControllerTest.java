package com.fiap.tech_challenge_web_streaming.infrastructure.video.controller;

import com.fiap.tech_challenge_web_streaming.domain.categoria.entity.Categoria;
import com.fiap.tech_challenge_web_streaming.domain.video.entity.Video;
import com.fiap.tech_challenge_web_streaming.infrastructure.video.dto.VideoPublicData;
import com.fiap.tech_challenge_web_streaming.usecase.video.BuscarVideoUseCase;
import com.fiap.tech_challenge_web_streaming.usecase.video.dto.IVideoPublicData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class BuscarVideoControllerTest {

    @Mock
    private BuscarVideoUseCase buscarVideoUseCase;

    private BuscarVideoController controller;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        controller = new BuscarVideoController(buscarVideoUseCase);
    }

    @Test
    void testGetVideoById() {
        Video video = new Video("Titulo", "Descricao", LocalDate.now(), Categoria.PETS, "url");
        VideoPublicData videoPublicData = new VideoPublicData(video);
        when(buscarVideoUseCase.execute(any(String.class))).thenReturn(Mono.just(video));

        Mono<ResponseEntity<VideoPublicData>> result = controller.getVideoById("testId");

        StepVerifier.create(result)
                .expectNext(new ResponseEntity<>(videoPublicData, HttpStatus.OK))
                .verifyComplete();
    }
}
