package com.fiap.tech_challenge_web_streaming.usecase.usuario;

import com.fiap.tech_challenge_web_streaming.domain.usuario.entity.Usuario;
import com.fiap.tech_challenge_web_streaming.domain.usuario.gateway.UsuarioGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

class BuscarTodosUsuariosUseCaseTest {

    @Mock
    private UsuarioGateway usuarioGateway;

    private BuscarTodosUsuariosUseCase buscarTodosUsuariosUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        buscarTodosUsuariosUseCase = new BuscarTodosUsuariosUseCase(usuarioGateway);
    }

    @Test
    void testExecute() {
        Usuario usuario = new Usuario();
        usuario.setId("1");

        when(usuarioGateway.listar()).thenReturn(Flux.just(usuario));

        Flux<Usuario> result = buscarTodosUsuariosUseCase.execute();

        StepVerifier.create(result)
                .expectNext(usuario)
                .verifyComplete();
    }
}
