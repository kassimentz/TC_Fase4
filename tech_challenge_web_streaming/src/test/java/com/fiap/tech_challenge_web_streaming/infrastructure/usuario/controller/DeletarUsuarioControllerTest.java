package com.fiap.tech_challenge_web_streaming.infrastructure.usuario.controller;

import com.fiap.tech_challenge_web_streaming.usecase.usuario.DeletarUsuarioUseCase;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

class DeletarUsuarioControllerTest {

    @Test
    void testDeletarUsuarioPorId() {
        DeletarUsuarioUseCase deletarUsuarioUseCase = mock(DeletarUsuarioUseCase.class);
        DeletarUsuarioController controller = new DeletarUsuarioController(deletarUsuarioUseCase);

        when(deletarUsuarioUseCase.execute(anyString())).thenReturn(Mono.empty());

        Mono<Void> response = controller.deletarUsuarioPorId("id").then();

        StepVerifier.create(response)
                .verifyComplete();

        verify(deletarUsuarioUseCase, times(1)).execute("id");
    }
}
