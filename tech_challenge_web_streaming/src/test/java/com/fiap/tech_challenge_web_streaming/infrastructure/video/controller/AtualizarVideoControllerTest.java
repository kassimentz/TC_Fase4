package com.fiap.tech_challenge_web_streaming.infrastructure.video.controller;

import com.fiap.tech_challenge_web_streaming.domain.categoria.entity.Categoria;
import com.fiap.tech_challenge_web_streaming.domain.video.entity.Video;
import com.fiap.tech_challenge_web_streaming.infrastructure.video.dto.VideoPublicData;
import com.fiap.tech_challenge_web_streaming.infrastructure.video.dto.VideoUpdateData;
import com.fiap.tech_challenge_web_streaming.usecase.video.AtualizarVideoUseCase;
import com.fiap.tech_challenge_web_streaming.usecase.video.dto.IVideoPublicData;
import com.fiap.tech_challenge_web_streaming.usecase.video.dto.IVideoUpdateData;
import org.junit.jupiter.api.Assertions;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class AtualizarVideoControllerTest {

    @Mock
    private AtualizarVideoUseCase atualizarVideoUseCase;

    private AtualizarVideoController controller;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        controller = new AtualizarVideoController(atualizarVideoUseCase);
    }

    @Test
    public void testAtualizarVideo() {
        Video video = new Video("Titulo", "Descricao", LocalDate.now(), Categoria.PETS);
        IVideoUpdateData dados = new VideoUpdateData("Titulo", "Descricao", "Pets", LocalDate.now());
        IVideoPublicData videoPublicData = new VideoPublicData(video);

        when(atualizarVideoUseCase.execute(anyString(), any())).thenReturn(Mono.just(video));

        ResponseEntity<IVideoPublicData> result = controller.atualizarVideo(dados, "testId").block();

        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(videoPublicData, result.getBody());

    }
}
