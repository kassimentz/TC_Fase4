package com.fiap.tech_challenge_web_streaming.infrastructure.video.gateway;

import com.fiap.tech_challenge_web_streaming.domain.video.exception.VideoNaoEncontradoException;
import com.fiap.tech_challenge_web_streaming.domain.video.entity.Video;
import com.fiap.tech_challenge_web_streaming.infrastructure.video.entityschema.VideoEntity;
import com.fiap.tech_challenge_web_streaming.infrastructure.video.repository.VideoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

class VideoStreamGatewayImplTest {

    @Mock
    private VideoRepository videoRepository;

    @Mock
    private ServerHttpResponse response;

    private VideoStreamGatewayImpl videoStreamGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        videoStreamGateway = new VideoStreamGatewayImpl(videoRepository);
    }

    @Test
    @Disabled
    void testStreamVideoById() {
        String videoId = "65adc2552f52f97eb1544d2e";
        VideoEntity videoEntity = new VideoEntity();
        videoEntity.setId(videoId);
        videoEntity.setQtVisualizacao(0L); // Set a value for qtVisualizacao
        when(videoRepository.findById(anyString())).thenReturn(Mono.just(videoEntity));
        when(response.bufferFactory()).thenReturn(mock(DataBufferFactory.class));
        when(response.writeWith(any())).thenReturn(Mono.empty());

        Mono<Void> result = videoStreamGateway.streamVideoById(videoId, response);

        StepVerifier.create(result)
                .verifyComplete();

        verify(videoRepository).findById(videoId);

    }

    @Test
    void streamVideoById_WhenVideoNotFound() {
        String videoId = "invalidId";
        when(videoRepository.findById(videoId)).thenReturn(Mono.empty());

        Mono<Void> result = videoStreamGateway.streamVideoById(videoId, response);

        StepVerifier.create(result)
                .expectError(VideoNaoEncontradoException.class)
                .verify();

        verify(videoRepository).findById(videoId);
        verify(videoRepository, never()).save(any());
        verify(response, never()).writeWith(any());
    }
}
