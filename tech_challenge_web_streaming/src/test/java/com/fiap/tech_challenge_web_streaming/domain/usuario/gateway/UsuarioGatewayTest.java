package com.fiap.tech_challenge_web_streaming.domain.usuario.gateway;

import com.fiap.tech_challenge_web_streaming.domain.usuario.entity.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

public class UsuarioGatewayTest {

    @Mock
    private UsuarioGateway usuarioGateway;

    @Mock
    private Usuario usuario;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(usuarioGateway.criar(usuario)).thenReturn(Mono.just(usuario));
        when(usuarioGateway.atualizar(usuario)).thenReturn(Mono.just(usuario));
        when(usuarioGateway.deletar(usuario)).thenReturn(Mono.empty());
        when(usuarioGateway.buscarPorId("1")).thenReturn(Mono.just(usuario));
    }

    @Test
    public void testCriar() {
        StepVerifier.create(usuarioGateway.criar(usuario))
                .expectNext(usuario)
                .verifyComplete();
    }

    @Test
    public void testAtualizar() {
        StepVerifier.create(usuarioGateway.atualizar(usuario))
                .expectNext(usuario)
                .verifyComplete();
    }

    @Test
    public void testDeletar() {
        StepVerifier.create(usuarioGateway.deletar(usuario))
                .verifyComplete();
    }

    @Test
    public void testBuscarPorId() {
        StepVerifier.create(usuarioGateway.buscarPorId("1"))
                .expectNext(usuario)
                .verifyComplete();
    }
}
