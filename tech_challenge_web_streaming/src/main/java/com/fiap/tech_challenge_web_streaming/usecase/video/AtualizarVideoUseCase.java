package com.fiap.tech_challenge_web_streaming.usecase.video;

import com.fiap.tech_challenge_web_streaming.domain.usuario.entity.Usuario;
import com.fiap.tech_challenge_web_streaming.domain.usuario.exception.UsuarioNaoEncontradoException;
import com.fiap.tech_challenge_web_streaming.domain.video.entity.Video;
import com.fiap.tech_challenge_web_streaming.domain.video.exception.VideoNaoEncontradoException;
import com.fiap.tech_challenge_web_streaming.domain.video.gateway.VideoGateway;
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

                    video.setTitulo(dados.titulo());
                    video.setDescricao(dados.descricao());
                    video.setDataPublicacao(dados.dataPublicacao());

                    //TODO colocar uma instancia de Categoria aqui, se necessário buscar uma para salvar através de um categoriaGateway
                    video.setCategoria(null);

                    return this.videoGateway.atualizar(video);
                });
    }


}
