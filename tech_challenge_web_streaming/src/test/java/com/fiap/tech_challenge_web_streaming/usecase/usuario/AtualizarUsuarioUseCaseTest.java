package com.fiap.tech_challenge_web_streaming.usecase.usuario;

import com.fiap.tech_challenge_web_streaming.domain.usuario.entity.Usuario;
import com.fiap.tech_challenge_web_streaming.domain.usuario.gateway.UsuarioGateway;
import com.fiap.tech_challenge_web_streaming.infrastructure.usuario.dto.UsuarioUpdateData;
import com.fiap.tech_challenge_web_streaming.usecase.usuario.dto.IUsuarioUpdateData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class AtualizarUsuarioUseCaseTest {

    @Mock
    private UsuarioGateway usuarioGateway;

    private AtualizarUsuarioUseCase atualizarUsuarioUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        atualizarUsuarioUseCase = new AtualizarUsuarioUseCase(usuarioGateway);
    }

    @Test
    public void testExecute() {
        Usuario usuario = new Usuario();
        usuario.setId("1");

        IUsuarioUpdateData usuarioUpdateData = new UsuarioUpdateData("nome atualizado", "email atualizado", null, null);

        when(usuarioGateway.buscarPorId(anyString())).thenReturn(Mono.just(usuario));
        when(usuarioGateway.atualizar(any(Usuario.class))).thenReturn(Mono.just(usuario));

        Mono<Usuario> result = atualizarUsuarioUseCase.execute("1", usuarioUpdateData);

        assert result != null;
    }
}
