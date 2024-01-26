package com.fiap.tech_challenge_web_streaming.usecase.usuario;

import com.fiap.tech_challenge_web_streaming.domain.usuario.entity.Usuario;
import com.fiap.tech_challenge_web_streaming.domain.usuario.exception.UsuarioNaoEncontradoException;
import com.fiap.tech_challenge_web_streaming.domain.usuario.gateway.UsuarioGateway;
import reactor.core.publisher.Mono;

public class BuscarUsuarioUseCase {
    private final UsuarioGateway usuarioGateway;


    public BuscarUsuarioUseCase(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    public Mono<Usuario> execute(String id) throws UsuarioNaoEncontradoException {
        return this.usuarioGateway.buscarPorId(id)
                .switchIfEmpty(Mono.error(UsuarioNaoEncontradoException::new));
    }


}
