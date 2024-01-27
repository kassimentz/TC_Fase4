package com.fiap.tech_challenge_web_streaming.usecase.video;

import com.fiap.tech_challenge_web_streaming.domain.video.entity.Video;
import com.fiap.tech_challenge_web_streaming.domain.video.exception.VideoNaoEncontradoException;
import com.fiap.tech_challenge_web_streaming.domain.video.gateway.VideoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class DeletarVideoUseCaseTest {

    @Mock
    private VideoGateway videoGateway;

    private DeletarVideoUseCase deletarVideoUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        deletarVideoUseCase = new DeletarVideoUseCase(videoGateway);
    }

    @Test
    void testExecute() {
        Video video = new Video();
        video.setId("1");

        when(videoGateway.buscarPorId(anyString())).thenReturn(Mono.just(video));
        when(videoGateway.deletar(video)).thenReturn(Mono.empty());

        Mono<Void> result = deletarVideoUseCase.execute("1");

        StepVerifier.create(result)
                .verifyComplete();
    }

    @Test
    void testExecuteVideoNaoEncontrado() {
        when(videoGateway.buscarPorId(anyString())).thenReturn(Mono.empty());

        Mono<Void> result = deletarVideoUseCase.execute("1");

        StepVerifier.create(result)
                .expectError(VideoNaoEncontradoException.class)
                .verify();
    }
}
