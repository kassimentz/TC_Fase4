package com.fiap.tech_challenge_web_streaming.usecase.usuario;

import com.fiap.tech_challenge_web_streaming.domain.usuario.entity.Usuario;
import com.fiap.tech_challenge_web_streaming.domain.usuario.gateway.UsuarioGateway;
import com.fiap.tech_challenge_web_streaming.infrastructure.usuario.dto.UsuarioRequestData;
import com.fiap.tech_challenge_web_streaming.usecase.usuario.dto.IUsuarioRequestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CriarUsuarioUseCaseTest {

    @Mock
    private UsuarioGateway usuarioGateway;

    private CriarUsuarioUseCase criarUsuarioUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        criarUsuarioUseCase = new CriarUsuarioUseCase(usuarioGateway);
    }

    @Test
    void testExecutar() {
        Usuario usuario = new Usuario();
        usuario.setId("1");

        IUsuarioRequestData usuarioRequestData = new UsuarioRequestData("nome", "email");

        when(usuarioGateway.criar(any(Usuario.class))).thenReturn(Mono.just(usuario));

        Mono<Usuario> result = criarUsuarioUseCase.executar(usuarioRequestData);

        StepVerifier.create(result)
                .expectNext(usuario)
                .verifyComplete();
    }
}
