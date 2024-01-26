package com.fiap.tech_challenge_web_streaming.usecase.video;

import com.fiap.tech_challenge_web_streaming.domain.video.entity.Video;
import com.fiap.tech_challenge_web_streaming.domain.video.gateway.VideoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

public class BuscarTodosOsVideosUseCaseTest {

    @Mock
    private VideoGateway videoGateway;

    private BuscarTodosOsVideosUseCase buscarTodosOsVideosUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        buscarTodosOsVideosUseCase = new BuscarTodosOsVideosUseCase(videoGateway);
    }

    @Test
    public void testExecute() {
        Video video = new Video();
        video.setId("1");

        when(videoGateway.buscarTodos()).thenReturn(Flux.just(video));

        Flux<Video> result = buscarTodosOsVideosUseCase.execute();

        StepVerifier.create(result)
                .expectNext(video)
                .verifyComplete();
    }
}
