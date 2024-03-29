package com.fiap.tech_challenge_web_streaming.usecase.usuario;

import com.fiap.tech_challenge_web_streaming.domain.usuario.exception.UsuarioNaoEncontradoException;
import com.fiap.tech_challenge_web_streaming.domain.usuario.gateway.UsuarioGateway;
import reactor.core.publisher.Mono;

public class DeletarUsuarioUseCase {

    private final UsuarioGateway usuarioGateway;


    public DeletarUsuarioUseCase(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    public Mono<Void> execute(String id) throws UsuarioNaoEncontradoException {

        return this.usuarioGateway.buscarPorId(id)
                .switchIfEmpty(Mono.error(new UsuarioNaoEncontradoException()))
                .flatMap(usuario -> usuarioGateway.deletar(usuario));
    }
}
