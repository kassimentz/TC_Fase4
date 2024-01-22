package com.fiap.tech_challenge_web_streaming.usecase.usuario;

import com.fiap.tech_challenge_web_streaming.domain.usuario.entity.Usuario;
import com.fiap.tech_challenge_web_streaming.domain.usuario.gateway.UsuarioGateway;
import reactor.core.publisher.Flux;

import java.util.List;

public class BuscarTodosUsuariosUseCase {

    private final UsuarioGateway usuarioGateway;

    public BuscarTodosUsuariosUseCase(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    public Flux<Usuario> execute() {
        return usuarioGateway.listar();
    }



}
