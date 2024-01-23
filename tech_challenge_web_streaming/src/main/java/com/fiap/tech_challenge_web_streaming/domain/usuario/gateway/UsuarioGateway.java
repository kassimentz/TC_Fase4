package com.fiap.tech_challenge_web_streaming.domain.usuario.gateway;

import com.fiap.tech_challenge_web_streaming.domain.usuario.entity.Usuario;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public interface UsuarioGateway {

    Mono<Usuario> criar(Usuario usuario);
    Mono<Usuario> atualizar (Usuario usuario);

    Mono<Void> deletar (Usuario usuario);

    Flux<Usuario> listar();

    Mono<Usuario> buscarPorId(String id);

}
