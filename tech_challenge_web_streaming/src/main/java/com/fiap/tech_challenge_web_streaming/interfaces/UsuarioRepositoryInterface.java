package com.fiap.tech_challenge_web_streaming.interfaces;

import com.fiap.tech_challenge_web_streaming.entities.Usuario;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UsuarioRepositoryInterface extends ReactiveMongoRepository<Usuario, String> {

    Mono<Usuario> findById(String id);

    Flux<Usuario> findAll();
}
