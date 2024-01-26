package com.fiap.tech_challenge_web_streaming.infrastructure.usuario.gateway;

import com.fiap.tech_challenge_web_streaming.domain.usuario.entity.Usuario;
import com.fiap.tech_challenge_web_streaming.infrastructure.usuario.entityschema.UsuarioEntity;
import com.fiap.tech_challenge_web_streaming.infrastructure.usuario.repository.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UsuarioDatabaseGatewayTest {

    @Mock
    private UsuarioRepository repository;

    private UsuarioDatabaseGateway gateway;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        gateway = new UsuarioDatabaseGateway(repository);
    }

    @Test
    public void testCriar() {
        Usuario usuario = new Usuario();
        UsuarioEntity usuarioEntity = new UsuarioEntity(usuario);
        when(repository.save(any(UsuarioEntity.class))).thenReturn(Mono.just(usuarioEntity));

        Usuario result = gateway.criar(usuario).block();

        Assertions.assertEquals(usuario.getId(), result.getId());
    }

    @Test
    public void testAtualizar() {
        Usuario usuario = new Usuario();
        UsuarioEntity usuarioEntity = new UsuarioEntity(usuario);
        usuarioEntity.setNome("Teste Atualizado");
        when(repository.save(any(UsuarioEntity.class))).thenReturn(Mono.just(usuarioEntity));
        usuario.setNome("Teste Atualizado");

        Usuario result = gateway.atualizar(usuario).block();

        Assertions.assertEquals(usuario.getId(), result.getId());
        Assertions.assertEquals(usuario.getNome(), result.getNome());
    }

    @Test
    public void testDeletar() {
        Usuario usuario = new Usuario();
        UsuarioEntity usuarioEntity = new UsuarioEntity(usuario);
        when(repository.delete(any(UsuarioEntity.class))).thenReturn(Mono.empty());

        StepVerifier.create(gateway.deletar(usuario))
                .verifyComplete();
    }

    @Test
    public void testListar() {
        Usuario usuario = new Usuario();
        UsuarioEntity usuarioEntity = new UsuarioEntity(usuario);
        when(repository.findAll()).thenReturn(Flux.fromIterable(Collections.singletonList(usuarioEntity)));

        List<Usuario> result = gateway.listar().collectList().block();

        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(usuario.getId(), result.get(0).getId());
    }

    @Test
    public void testBuscarPorId() {
        Usuario usuario = new Usuario();
        UsuarioEntity usuarioEntity = new UsuarioEntity(usuario);
        when(repository.findById(any(String.class))).thenReturn(Mono.just(usuarioEntity));

        Usuario result = gateway.buscarPorId("testId").block();

        Assertions.assertEquals(usuario.getId(), result.getId());
    }
}
