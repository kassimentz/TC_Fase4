package com.fiap.tech_challenge_web_streaming.infrastructure.video.controller;

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

public class CriarVideoControllerTest {

    @Mock
    private CriarVideoUseCase criarVideoUseCase;

    private CriarVideoController controller;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        controller = new CriarVideoController(criarVideoUseCase);
    }

    @Test
    public void testCreateVideo() {
        Video video = new Video("Titulo", "Descricao", LocalDate.now(), Categoria.PETS);
        IVideoRequestData videoData = new VideoRequestData("Titulo", "Descricao", "Pets", LocalDate.now());
        IVideoPublicData videoPublicData = new VideoPublicData(video);
        when(criarVideoUseCase.execute(any(IVideoRequestData.class))).thenReturn(Mono.just(video));

        Mono<ResponseEntity<IVideoPublicData>> result = controller.createVideo(videoData);

        StepVerifier.create(result)
                .expectNext(new ResponseEntity<>(videoPublicData, HttpStatus.CREATED))
                .verifyComplete();
    }
}
