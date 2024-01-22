package com.fiap.tech_challenge_web_streaming.infrastructure.usuario.gateway;


import com.fiap.tech_challenge_web_streaming.domain.usuario.entity.Usuario;
import com.fiap.tech_challenge_web_streaming.domain.usuario.gateway.UsuarioGateway;
import com.fiap.tech_challenge_web_streaming.infrastructure.usuario.entityschema.UsuarioEntity;
import com.fiap.tech_challenge_web_streaming.infrastructure.usuario.repository.UsuarioRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public class UsuarioDatabaseGateway implements UsuarioGateway {

    private final UsuarioRepository repository;

    public UsuarioDatabaseGateway(UsuarioRepository repository) {
        this.repository = repository;
    }


    @Override
    public Mono<Usuario> criar(Usuario usuario) {
        UsuarioEntity usuarioEntity = new UsuarioEntity(usuario);
        return repository.save(usuarioEntity).map(UsuarioEntity::toUsuario);
    }

    @Override
    public Mono<Usuario> atualizar(Usuario usuario) {
        UsuarioEntity usuarioEntity = new UsuarioEntity(usuario);
        return repository.save(usuarioEntity).map(UsuarioEntity::toUsuario);
    }

    @Override
    public Mono<Void> deletar(Usuario usuario) {
        UsuarioEntity usuarioEntity = new UsuarioEntity(usuario);
        return repository.delete(usuarioEntity);
    }

    @Override
    public Flux<Usuario> listar() {
        return repository.findAll().map(UsuarioEntity::toUsuario);
    }

    @Override
    public Mono<Usuario> buscarPorId(String id) {
         return repository.findById(id).map(
                 UsuarioEntity::toUsuario
         );
    }

    //TODO Implement After Video creation
    @Override
    public Mono<Usuario> adicionarFavorito() {
        return null;
    }
}


