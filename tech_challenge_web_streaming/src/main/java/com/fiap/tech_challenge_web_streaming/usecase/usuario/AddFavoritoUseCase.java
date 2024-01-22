package com.fiap.tech_challenge_web_streaming.usecase.usuario;

import com.fiap.tech_challenge_web_streaming.domain.usuario.entity.Usuario;
import com.fiap.tech_challenge_web_streaming.domain.usuario.gateway.UsuarioGateway;
import com.fiap.tech_challenge_web_streaming.usecase.usuario.dto.IUsuarioAddFavoritoRequestData;
import reactor.core.publisher.Mono;

public class AddFavoritoUseCase {

    private final UsuarioGateway usuarioGateway;

    public AddFavoritoUseCase(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    public Mono<Usuario> execute(String id, IUsuarioAddFavoritoRequestData videoId) {
        //TODO incluir dados do vídeo aqui
        return usuarioGateway.adicionarFavorito();
    }

}
