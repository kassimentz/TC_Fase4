package com.fiap.tech_challenge_web_streaming.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@DataMongoTest
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepositoryInterface usuarioRepository;

    @BeforeEach
    public void setUp() {
        Usuario usuario = new Usuario();
        usuario.setId("1234");
        usuario.setNome("Test Name");
        usuario.setEmail("test@email.com");
        usuarioRepository.save(usuario).block();
    }

    @Test
    public void testFindById() {
        Mono<Usuario> usuarioMono = usuarioRepository.findById("1234");

        StepVerifier.create(usuarioMono)
                .expectNextMatches(usuario ->
                        usuario.getId().equals("1234") &&
                                usuario.getNome().equals("Test Name") &&
                                usuario.getEmail().equals("test@email.com")
                )
                .verifyComplete();
    }

    @Test
    public void testFindAll() {
        Flux<Usuario> usuarioFlux = usuarioRepository.findAll();

        StepVerifier.create(usuarioFlux)
                .expectNextMatches(usuario ->
                        usuario.getId().equals("1234") &&
                                usuario.getNome().equals("Test Name") &&
                                usuario.getEmail().equals("test@email.com")
                )
                .verifyComplete();
    }
}
