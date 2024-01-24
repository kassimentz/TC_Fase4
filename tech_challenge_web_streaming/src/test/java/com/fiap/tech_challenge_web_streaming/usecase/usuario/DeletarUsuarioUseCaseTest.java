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

public class DeletarUsuarioUseCaseTest {

    @Mock
    private UsuarioGateway usuarioGateway;

    private DeletarUsuarioUseCase deletarUsuarioUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        deletarUsuarioUseCase = new DeletarUsuarioUseCase(usuarioGateway);
    }

    @Test
    public void testExecute() {
        Usuario usuario = new Usuario();
        usuario.setId("1");

        when(usuarioGateway.buscarPorId(anyString())).thenReturn(Mono.just(usuario));
        when(usuarioGateway.deletar(usuario)).thenReturn(Mono.empty());

        Mono<Void> result = deletarUsuarioUseCase.execute("1");

        StepVerifier.create(result)
                .verifyComplete();
    }

    @Test
    public void testExecuteUsuarioNaoEncontrado() {
        when(usuarioGateway.buscarPorId(anyString())).thenReturn(Mono.empty());

        Mono<Void> result = deletarUsuarioUseCase.execute("1");

        StepVerifier.create(result)
                .expectError(UsuarioNaoEncontradoException.class)
                .verify();
    }
}
