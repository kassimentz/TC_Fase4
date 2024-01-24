package com.fiap.tech_challenge_web_streaming.usecase.usuario;

import com.fiap.tech_challenge_web_streaming.domain.usuario.entity.Usuario;
import com.fiap.tech_challenge_web_streaming.domain.usuario.exception.UsuarioNaoEncontradoException;
import com.fiap.tech_challenge_web_streaming.domain.usuario.gateway.UsuarioGateway;
import com.fiap.tech_challenge_web_streaming.usecase.usuario.dto.IUsuarioUpdateData;
import reactor.core.publisher.Mono;

public class AtualizarUsuarioUseCase {

    private final UsuarioGateway usuarioGateway;

    public AtualizarUsuarioUseCase(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    public Mono<Usuario> execute(String id, IUsuarioUpdateData dados) {
        return this.usuarioGateway.buscarPorId(id)
                .switchIfEmpty(Mono.error(new UsuarioNaoEncontradoException()))
                .flatMap(usuario -> updateUsuario(usuario, dados))
                .flatMap(this.usuarioGateway::atualizar);
    }

    private Mono<Usuario> updateUsuario(Usuario usuario, IUsuarioUpdateData dados) {
        usuario.setNome(dados.nome());
        usuario.setEmail(dados.email());
        usuario.setFavoritos(dados.favoritos());
        usuario.setRecomendados(dados.recomendados());
        return Mono.just(usuario);
    }
}