package com.fiap.tech_challenge_web_streaming.usecase.video;

import com.fiap.tech_challenge_web_streaming.domain.categoria.entity.Categoria;
import com.fiap.tech_challenge_web_streaming.domain.video.entity.Video;
import com.fiap.tech_challenge_web_streaming.domain.video.exception.VideoNaoEncontradoException;
import com.fiap.tech_challenge_web_streaming.domain.video.gateway.VideoGateway;
import com.fiap.tech_challenge_web_streaming.usecase.video.dto.IVideoRequestData;
import com.fiap.tech_challenge_web_streaming.usecase.video.dto.IVideoUpdateData;
import reactor.core.publisher.Mono;

public class AtualizarVideoUseCase {

    private final VideoGateway videoGateway;

    public AtualizarVideoUseCase(VideoGateway videoGateway) {
        this.videoGateway = videoGateway;
    }

    public Mono<Video> execute(String id, IVideoUpdateData dados) throws VideoNaoEncontradoException {

        return this.videoGateway.buscarPorId(id)
                .switchIfEmpty(Mono.error(new VideoNaoEncontradoException()))
                .flatMap(video -> {

                    //implementar possíveis validações negociais aqui

                    if (dados.titulo() != null)
                    video.setTitulo(dados.titulo());

                    if(dados.descricao() != null)
                    video.setDescricao(dados.descricao());

                    if (dados.categoria() != null)
                    video.setCategoria(Categoria.valueOf(dados.categoria()));

                    return this.videoGateway.atualizar(video);
                });
    }


}
