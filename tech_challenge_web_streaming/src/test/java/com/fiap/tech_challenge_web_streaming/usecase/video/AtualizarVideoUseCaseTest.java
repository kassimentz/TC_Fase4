package com.fiap.tech_challenge_web_streaming.usecase.video;

import com.fiap.tech_challenge_web_streaming.domain.categoria.entity.Categoria;
import com.fiap.tech_challenge_web_streaming.domain.video.entity.Video;
import com.fiap.tech_challenge_web_streaming.domain.video.exception.VideoNaoEncontradoException;
import com.fiap.tech_challenge_web_streaming.domain.video.gateway.VideoGateway;
import com.fiap.tech_challenge_web_streaming.infrastructure.video.dto.VideoUpdateData;
import com.fiap.tech_challenge_web_streaming.usecase.video.dto.IVideoUpdateData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class AtualizarVideoUseCaseTest {

    @Mock
    private VideoGateway videoGateway;

    private AtualizarVideoUseCase atualizarVideoUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        atualizarVideoUseCase = new AtualizarVideoUseCase(videoGateway);
    }

    @Test
    public void testExecute() {
        Video video = new Video();
        video.setId("1");

        IVideoUpdateData videoUpdateData = new VideoUpdateData("test title", "test description", Categoria.PETS.name(), LocalDate.now());

        when(videoGateway.buscarPorId(anyString())).thenReturn(Mono.just(video));
        when(videoGateway.atualizar(any(Video.class))).thenReturn(Mono.just(video));

        Mono<Video> result = atualizarVideoUseCase.execute("1", videoUpdateData);

        StepVerifier.create(result)
                .expectNext(video)
                .verifyComplete();
    }

    @Test
    public void testExecuteVideoNaoEncontrado() {
        when(videoGateway.buscarPorId(anyString())).thenReturn(Mono.empty());

        IVideoUpdateData videoUpdateData = new VideoUpdateData("test title", "test description", Categoria.PETS.name(), LocalDate.now());

        Mono<Video> result = atualizarVideoUseCase.execute("1", videoUpdateData);

        StepVerifier.create(result)
                .expectError(VideoNaoEncontradoException.class)
                .verify();
    }
}
