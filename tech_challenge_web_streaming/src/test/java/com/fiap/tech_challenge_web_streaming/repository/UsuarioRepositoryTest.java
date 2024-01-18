package com.fiap.tech_challenge_web_streaming.repository;

import com.fiap.tech_challenge_web_streaming.entities.Usuario;
import com.fiap.tech_challenge_web_streaming.interfaces.UsuarioRepositoryInterface;
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

    private Usuario usuario;

    @BeforeEach
    public void setUp() {
        usuario = new Usuario();
        usuario.setId("1234");
        usuario.setNome("Test Name");
        usuario.setEmail("test@email.com");
        usuarioRepository.save(usuario).block();
    }

    @Test
    public void testFindById() {
        Mono<Usuario> usuarioMono = usuarioRepository.findById("1234");

        StepVerifier.create(usuarioMono)
                .expectNextMatches(foundUsuario ->
                        foundUsuario.getId().equals(usuario.getId()) &&
                                foundUsuario.getNome().equals(usuario.getNome()) &&
                                foundUsuario.getEmail().equals(usuario.getEmail())
                )
                .verifyComplete();
    }

    @Test
    public void testFindAll() {
        Flux<Usuario> usuarioFlux = usuarioRepository.findAll();

        StepVerifier.create(usuarioFlux)
                .expectNextMatches(foundUsuario ->
                        foundUsuario.getId().equals(usuario.getId()) &&
                                foundUsuario.getNome().equals(usuario.getNome()) &&
                                foundUsuario.getEmail().equals(usuario.getEmail())
                )
                .verifyComplete();
    }

    @Test
    public void testSave() {
        Usuario newUsuario = new Usuario();
        newUsuario.setId("5678");
        newUsuario.setNome("New Test Name");
        newUsuario.setEmail("newtest@email.com");

        Mono<Usuario> savedUsuarioMono = usuarioRepository.save(newUsuario);

        StepVerifier.create(savedUsuarioMono)
                .expectNextMatches(savedUsuario ->
                        savedUsuario.getId().equals(newUsuario.getId()) &&
                                savedUsuario.getNome().equals(newUsuario.getNome()) &&
                                savedUsuario.getEmail().equals(newUsuario.getEmail())
                )
                .verifyComplete();
    }

    @Test
    public void testDelete() {
        Mono<Void> deleted = usuarioRepository.delete(usuario);

        StepVerifier.create(deleted)
                .verifyComplete();

        Mono<Usuario> usuarioMono = usuarioRepository.findById(usuario.getId());

        StepVerifier.create(usuarioMono)
                .verifyComplete();
    }
}
