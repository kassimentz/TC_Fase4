package com.fiap.tech_challenge_web_streaming.infrastructure.video.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fiap.tech_challenge_web_streaming.domain.categoria.entity.Categoria;
import com.fiap.tech_challenge_web_streaming.domain.video.entity.Video;
import com.fiap.tech_challenge_web_streaming.infrastructure.video.dto.VideoPublicData;
import com.fiap.tech_challenge_web_streaming.infrastructure.video.dto.VideoRequestData;
import com.fiap.tech_challenge_web_streaming.usecase.video.CriarVideoUseCase;
import com.fiap.tech_challenge_web_streaming.usecase.video.dto.IVideoPublicData;
import com.fiap.tech_challenge_web_streaming.usecase.video.dto.IVideoRequestData;
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

class CriarVideoControllerTest {

    @Mock
    private CriarVideoUseCase criarVideoUseCase;

    private CriarVideoController controller;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        controller = new CriarVideoController(criarVideoUseCase);
    }

    @Test
    void testCreateVideo() throws JsonProcessingException{
        Video video = new Video("Titulo", "Descricao", LocalDate.now(), Categoria.PETS, "url");
        VideoPublicData videoPublicData = new VideoPublicData(video);
        when(criarVideoUseCase.execute(any(IVideoRequestData.class), any())).thenReturn(Mono.just(video));

        Mono<ResponseEntity<VideoPublicData>> result = controller.createVideo(new VideoRequestData("Titulo", "Descricao",  "Pets", LocalDate.now()), null);

        StepVerifier.create(result)
                .expectNext(new ResponseEntity<>(videoPublicData, HttpStatus.CREATED))
                .verifyComplete();
    }
}
