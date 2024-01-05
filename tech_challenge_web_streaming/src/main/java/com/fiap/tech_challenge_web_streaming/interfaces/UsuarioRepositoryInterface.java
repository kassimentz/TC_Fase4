package com.fiap.tech_challenge_web_streaming.interfaces;

import com.fiap.tech_challenge_web_streaming.entities.Usuario;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UsuarioRepositoryInterface extends ReactiveMongoRepository<Usuario, Long> {

    Mono<Usuario> findById(Long id);

    Flux<Usuario> findAll();
}
