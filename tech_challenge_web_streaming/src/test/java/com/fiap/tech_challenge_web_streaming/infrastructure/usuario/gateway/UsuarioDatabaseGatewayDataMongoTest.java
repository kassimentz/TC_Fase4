package com.fiap.tech_challenge_web_streaming.infrastructure.usuario.gateway;

import com.fiap.tech_challenge_web_streaming.domain.usuario.entity.Usuario;
import com.fiap.tech_challenge_web_streaming.infrastructure.usuario.entityschema.UsuarioEntity;
import com.fiap.tech_challenge_web_streaming.infrastructure.usuario.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
 class UsuarioDatabaseGatewayDataMongoTest {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private ReactiveMongoTemplate reactiveMongoTemplate;

    private UsuarioDatabaseGateway usuarioDatabaseGateway;

    @BeforeEach
     void setUp() {
        usuarioDatabaseGateway = new UsuarioDatabaseGateway(repository);
        for (int i = 1; i <= 5; i++) {
            UsuarioEntity usuarioEntity = new UsuarioEntity();
            usuarioEntity.setId(String.valueOf(i));
            reactiveMongoTemplate.save(usuarioEntity).block();
        }
    }

    @Test
     void testCriar() {
        Usuario usuario = new Usuario();
        usuario.setId("1");

        Mono<Usuario> result = usuarioDatabaseGateway.criar(usuario);

        StepVerifier.create(result)
                .assertNext(createdUsuario -> assertThat(createdUsuario).isNotNull())
                .verifyComplete();
    }

    @Test
     void testAtualizar() {
        Usuario usuario = new Usuario();
        usuario.setId("1");

        Mono<Usuario> result = usuarioDatabaseGateway.atualizar(usuario);

        StepVerifier.create(result)
                .assertNext(updatedUsuario -> assertThat(updatedUsuario).isNotNull())
                .verifyComplete();
    }

    @Test
     void testDeletar() {
        Usuario usuario = new Usuario();
        usuario.setId("1");

        Mono<Void> result = usuarioDatabaseGateway.deletar(usuario);

        StepVerifier.create(result)
                .verifyComplete();
    }

    @Test
     void testListar() {
        Flux<Usuario> result = usuarioDatabaseGateway.listar();

        StepVerifier.create(result.collectList())
                .assertNext(list -> assertThat(list).hasSize(5))
                .verifyComplete();
    }

    @Test
     void testBuscarPorId() {
        Mono<Usuario> result = usuarioDatabaseGateway.buscarPorId("1");

        StepVerifier.create(result)
                .assertNext(usuario -> assertThat(usuario).isNotNull())
                .verifyComplete();
    }
}
