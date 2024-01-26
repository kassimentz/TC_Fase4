package com.fiap.tech_challenge_web_streaming.usecase.usuario;

import com.fiap.tech_challenge_web_streaming.domain.usuario.entity.Usuario;
import com.fiap.tech_challenge_web_streaming.domain.usuario.gateway.UsuarioGateway;
import com.fiap.tech_challenge_web_streaming.usecase.usuario.dto.IUsuarioRequestData;
import reactor.core.publisher.Mono;

public class CriarUsuarioUseCase {

    private final UsuarioGateway usuarioGateway;

    public CriarUsuarioUseCase(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }


    public Mono<Usuario> executar(IUsuarioRequestData dados) {

        Usuario usuario = new Usuario(dados.nome(), dados.email());

       return this.usuarioGateway.criar(usuario);

    }
}
