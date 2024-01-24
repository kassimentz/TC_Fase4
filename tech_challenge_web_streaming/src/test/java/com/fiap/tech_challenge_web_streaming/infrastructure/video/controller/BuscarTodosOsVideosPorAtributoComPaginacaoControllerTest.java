package com.fiap.tech_challenge_web_streaming.infrastructure.video.controller;

import com.fiap.tech_challenge_web_streaming.domain.categoria.entity.Categoria;
import com.fiap.tech_challenge_web_streaming.domain.video.entity.Video;
import com.fiap.tech_challenge_web_streaming.domain.video.factories.CriteriosBuscaVideo;
import com.fiap.tech_challenge_web_streaming.domain.video.factories.PageData;
import com.fiap.tech_challenge_web_streaming.infrastructure.video.dto.VideoPublicData;
import com.fiap.tech_challenge_web_streaming.usecase.video.BuscarTodosOsVideosPorAtributoComPaginacaoUseCase;
import com.fiap.tech_challenge_web_streaming.usecase.video.dto.IVideoPublicData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class BuscarTodosOsVideosPorAtributoComPaginacaoControllerTest {

    @Mock
    private BuscarTodosOsVideosPorAtributoComPaginacaoUseCase buscarTodosOsVideosPorAtributoComPaginacaoUseCase;

    private BuscarTodosOsVideosPorAtributoComPaginacaoController controller;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        controller = new BuscarTodosOsVideosPorAtributoComPaginacaoController(buscarTodosOsVideosPorAtributoComPaginacaoUseCase);
    }

    @Test
    public void testGetAllVideosPaginatedAndFiltered() {
        Video video = new Video("Titulo", "Descricao", LocalDate.now(), Categoria.PETS);
        VideoPublicData videoPublicData = new VideoPublicData(video);
        when(buscarTodosOsVideosPorAtributoComPaginacaoUseCase.execute(any(PageData.class), any(CriteriosBuscaVideo.class))).thenReturn(Flux.just(video));

        Flux<ResponseEntity<IVideoPublicData>> result = controller.getAllVideosPaginatedAndFiltered(0, 10, "DESC", "id", "Titulo", LocalDate.now(), "Pets");

        StepVerifier.create(result)
                .expectNext(new ResponseEntity<>(videoPublicData, HttpStatus.OK))
                .verifyComplete();
    }
}