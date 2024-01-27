package com.fiap.tech_challenge_web_streaming.usecase.usuario;

import com.fiap.tech_challenge_web_streaming.domain.usuario.entity.Usuario;
import com.fiap.tech_challenge_web_streaming.domain.usuario.gateway.UsuarioGateway;
import com.fiap.tech_challenge_web_streaming.domain.video.entity.Video;
import com.fiap.tech_challenge_web_streaming.domain.video.gateway.VideoGateway;
import com.fiap.tech_challenge_web_streaming.usecase.usuario.dto.IUsuarioAddFavoritoRequestData;
import reactor.core.publisher.Mono;

public class AddFavoritoUseCase {

    private final UsuarioGateway usuarioGateway;

    private final VideoGateway videoGateway;

    public AddFavoritoUseCase(UsuarioGateway usuarioGateway, VideoGateway videoGateway) {
        this.usuarioGateway = usuarioGateway;
        this.videoGateway = videoGateway;
    }

    public Mono<Usuario> execute(String usuarioId, IUsuarioAddFavoritoRequestData videoRequest) {
        return adicionarVideoFavorito(usuarioId, videoRequest);
    }

    private Mono<Usuario> adicionarVideoFavorito(String usuarioId,  IUsuarioAddFavoritoRequestData videoRequest){
        return usuarioGateway.buscarPorId(usuarioId)
                .zipWith(videoGateway.buscarPorId(videoRequest.videoId()))
                .flatMap(tuple -> {
                    Usuario user = tuple.getT1();
                    Video video = tuple.getT2();
                    user.addFavorito(video);
                    video.favoritarVideoPorUsuario(user.getId());
                    return Mono.zip(usuarioGateway.atualizar(user), videoGateway.atualizar(video))
                            .thenReturn(user);
                });
    }

}
