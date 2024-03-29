package com.fiap.tech_challenge_web_streaming.domain.video.gateway;

import com.fiap.tech_challenge_web_streaming.domain.video.entity.Video;
import com.fiap.tech_challenge_web_streaming.domain.video.factories.CriteriosBuscaVideo;
import com.fiap.tech_challenge_web_streaming.domain.video.factories.PageData;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface VideoGateway {


    Flux<Video> buscarTodosOsVideosPorAtributo(PageData dadosPaginacao, CriteriosBuscaVideo criteriosBuscaVideo);

    Flux<Video> buscarTodos();

    Mono<Video> buscarPorId(String id);

    Mono<Video> atualizar(Video video);

    Mono<Void> deletar(Video video);

    Mono<Video> criar (Video video);

    Mono<Long> count();

    Mono<Long> buscarQuantidadeVisualizacoes();

}
