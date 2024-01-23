package com.fiap.tech_challenge_web_streaming.infrastructure.usuario.controller;

import com.fiap.tech_challenge_web_streaming.domain.categoria.entity.Categoria;
import com.fiap.tech_challenge_web_streaming.domain.video.entity.Video;
import com.fiap.tech_challenge_web_streaming.infrastructure.video.dto.VideoPublicData;
import com.fiap.tech_challenge_web_streaming.usecase.usuario.BuscarRecomendacoesUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

public class BuscarRecomendacoesUsuarioControllerTest {

    @Test
    public void testBuscarRecomendacoes() {
        BuscarRecomendacoesUseCase buscarRecomendacoesUseCase = mock(BuscarRecomendacoesUseCase.class);
        BuscarRecomendacoesUsuarioController controller = new BuscarRecomendacoesUsuarioController(buscarRecomendacoesUseCase);

        Video video = new Video();
        video.setId("videoId");
        video.setTitulo("titulo");
        video.setCategoria(Categoria.PETS);

        when(buscarRecomendacoesUseCase.execute(anyString())).thenReturn(Flux.just(video));

        ResponseEntity<Flux<VideoPublicData>> response = controller.buscarRecomendacoes("id");

        StepVerifier.create(response.getBody())
                .assertNext(videoPublicData -> {
                    verify(buscarRecomendacoesUseCase).execute("id");
                    assert videoPublicData.id().equals(video.getId());
                    assert videoPublicData.titulo().equals(video.getTitulo());
                    assert response.getStatusCode() == HttpStatus.OK;
                })
                .verifyComplete();
    }
}
