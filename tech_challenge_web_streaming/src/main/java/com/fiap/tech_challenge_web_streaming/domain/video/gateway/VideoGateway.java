package com.fiap.tech_challenge_web_streaming.domain.video.gateway;

import com.fiap.tech_challenge_web_streaming.domain.video.entity.Video;
import com.fiap.tech_challenge_web_streaming.domain.video.factories.CriteriosBuscaVideo;
import com.fiap.tech_challenge_web_streaming.domain.video.factories.PageData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface VideoGateway {


    //TODO olhar aqui para pegar a paginação a nível de infra: https://github.com/kassimentz/TC_Fase4/blob/main/tech_challenge_web_streaming/src/main/java/com/fiap/tech_challenge_web_streaming/gateways/VideoGateway.java
    Flux<Video> buscarTodosOsVideosPorAtributo(PageData dadosPaginacao, CriteriosBuscaVideo criteriosBuscaVideo);

    Flux<Video> buscarTodos();

    Mono<Video> buscarPorId(String id);

    Mono<Video> atualizar(Video video);

    Mono<Void> deletar(Video video);

    Mono<Video> criar (Video video);

    Mono<Long> count();

}
