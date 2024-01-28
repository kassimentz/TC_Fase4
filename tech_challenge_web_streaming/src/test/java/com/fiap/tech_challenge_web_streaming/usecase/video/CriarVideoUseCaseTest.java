package com.fiap.tech_challenge_web_streaming.usecase.video;

import com.fiap.tech_challenge_web_streaming.domain.categoria.entity.Categoria;
import com.fiap.tech_challenge_web_streaming.domain.video.entity.Video;
import com.fiap.tech_challenge_web_streaming.domain.video.gateway.VideoFileGateway;
import com.fiap.tech_challenge_web_streaming.domain.video.gateway.VideoGateway;
import com.fiap.tech_challenge_web_streaming.infrastructure.video.dto.VideoRequestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.File;
import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CriarVideoUseCaseTest {

    @Mock
    private VideoGateway videoGateway;

    @Mock
    private VideoFileGateway videoFileGateway;
    private CriarVideoUseCase criarVideoUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        criarVideoUseCase = new CriarVideoUseCase(videoGateway, videoFileGateway);
    }

    @Test
    void testExecute() {
        Video video = new Video("Titulo", "Descricao",  Categoria.PETS, "url");
        String mockUrl = "filePathMock";
        FilePart part = mock(FilePart.class);
        when(videoGateway.criar(any(Video.class))).thenReturn(Mono.just(video));
        when(videoFileGateway.salvarArquivoVideo(any(FilePart.class))).thenReturn(Mono.just(mockUrl));

        Mono<Video> result = criarVideoUseCase.execute(new VideoRequestData("Titulo", "Descricao", "PETS"), part);

        StepVerifier.create(result)
                .expectNext(video)
                .verifyComplete();
    }
}
