package com.fiap.tech_challenge_web_streaming.usecase.video;

import com.fiap.tech_challenge_web_streaming.domain.video.entity.Video;
import com.fiap.tech_challenge_web_streaming.domain.video.gateway.VideoGateway;
import com.fiap.tech_challenge_web_streaming.infrastructure.video.dto.VideoRequestData;
import com.fiap.tech_challenge_web_streaming.usecase.video.dto.IVideoRequestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CriarVideoUseCaseTest {

    @Mock
    private VideoGateway videoGateway;

    private CriarVideoUseCase criarVideoUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        criarVideoUseCase = new CriarVideoUseCase(videoGateway);
    }

    @Test
    public void testExecute() {
        Video video = new Video("test title", "test description", LocalDate.now(), null);

        IVideoRequestData videoRequestData = new VideoRequestData("test title", "test description", null, LocalDate.now());

        when(videoGateway.criar(any(Video.class))).thenReturn(Mono.just(video));

        Mono<Video> result = criarVideoUseCase.execute(videoRequestData);

        StepVerifier.create(result)
                .expectNext(video)
                .verifyComplete();
    }
}