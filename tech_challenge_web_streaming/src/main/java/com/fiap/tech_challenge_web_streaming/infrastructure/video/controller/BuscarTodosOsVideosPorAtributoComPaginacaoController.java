package com.fiap.tech_challenge_web_streaming.infrastructure.video.controller;

import com.fiap.tech_challenge_web_streaming.domain.video.factories.CriteriosBuscaVideo;
import com.fiap.tech_challenge_web_streaming.domain.video.factories.PageData;
import com.fiap.tech_challenge_web_streaming.infrastructure.video.dto.VideoPublicData;
import com.fiap.tech_challenge_web_streaming.usecase.video.BuscarTodosOsVideosPorAtributoComPaginacaoUseCase;
import com.fiap.tech_challenge_web_streaming.usecase.video.dto.IVideoPublicData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
@Tag(name = "Vídeo", description = "Vídeo API")
@RestController
public class BuscarTodosOsVideosPorAtributoComPaginacaoController {

    private final BuscarTodosOsVideosPorAtributoComPaginacaoUseCase buscarTodosOsVideosPorAtributoComPaginacaoUseCase;


    public BuscarTodosOsVideosPorAtributoComPaginacaoController(BuscarTodosOsVideosPorAtributoComPaginacaoUseCase buscarTodosOsVideosPorAtributoComPaginacaoUseCase) {
        this.buscarTodosOsVideosPorAtributoComPaginacaoUseCase = buscarTodosOsVideosPorAtributoComPaginacaoUseCase;
    }

    @GetMapping("/videos/filtrar")
    @Operation(summary = "Buscar Vídeos com paginação e filtrando por titulo/categoria/dataPublicacao")
    public ResponseEntity<Flux<VideoPublicData>> getAllVideosPaginatedAndFiltered(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "direcao", defaultValue = "DESC") String direcao,
            @RequestParam(value = "ordenacao", defaultValue = "id") String ordenacao,
            @RequestParam(value = "titulo", required = false) String titulo,
            @RequestParam(value = "dataPublicacao", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataPublicacao,
            @RequestParam(value = "categoria", required = false) String categoria) {

        PageData data = PageData.newPageBuilder()
                .page(page)
                .size(size)
                .direcao(direcao)
                .ordenacao(ordenacao)
                .build();

        CriteriosBuscaVideo criteriosBuscaVideo = CriteriosBuscaVideo.newBuilder()
                .titulo(titulo)
                .categoria(categoria)
                .dataPublicacao(dataPublicacao)
                .build();


        Flux<VideoPublicData> videoPublicDataFlux = this.buscarTodosOsVideosPorAtributoComPaginacaoUseCase.execute(data, criteriosBuscaVideo)
                .map(VideoPublicData::new);

        return new ResponseEntity<>(videoPublicDataFlux, HttpStatus.OK);

    }

}
