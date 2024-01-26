package com.fiap.tech_challenge_web_streaming.usecase.usuario;

import com.fiap.tech_challenge_web_streaming.domain.usuario.entity.Usuario;
import com.fiap.tech_challenge_web_streaming.domain.usuario.exception.UsuarioNaoEncontradoException;
import com.fiap.tech_challenge_web_streaming.domain.usuario.gateway.UsuarioGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class BuscarUsuarioUseCaseTest {

    @Mock
    private UsuarioGateway usuarioGateway;

    private BuscarUsuarioUseCase buscarUsuarioUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        buscarUsuarioUseCase = new BuscarUsuarioUseCase(usuarioGateway);
    }

    @Test
    public void testExecute() {
        Usuario usuario = new Usuario();
        usuario.setId("1");

        when(usuarioGateway.buscarPorId(anyString())).thenReturn(Mono.just(usuario));

        Mono<Usuario> result = buscarUsuarioUseCase.execute("1");

        StepVerifier.create(result)
                .expectNext(usuario)
                .verifyComplete();
    }

    @Test
    public void testExecuteUsuarioNaoEncontrado() {
        when(usuarioGateway.buscarPorId(anyString())).thenReturn(Mono.empty());

        Mono<Usuario> result = buscarUsuarioUseCase.execute("1");

        StepVerifier.create(result)
                .expectError(UsuarioNaoEncontradoException.class)
                .verify();
    }
}
