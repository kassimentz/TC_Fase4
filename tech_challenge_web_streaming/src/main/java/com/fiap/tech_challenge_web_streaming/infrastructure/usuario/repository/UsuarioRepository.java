package com.fiap.tech_challenge_web_streaming.infrastructure.usuario.repository;

import com.fiap.tech_challenge_web_streaming.infrastructure.usuario.entityschema.UsuarioEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;


public interface UsuarioRepository extends ReactiveMongoRepository <UsuarioEntity, String> {




}
