package com.fiap.tech_challenge_web_streaming.infrastructure.usuario.repository;

import com.fiap.tech_challenge_web_streaming.infrastructure.usuario.entityschema.UsuarioEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;


public interface UsuarioRepository extends ReactiveMongoRepository <UsuarioEntity, String> {




}
