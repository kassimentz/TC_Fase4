package com.fiap.tech_challenge_web_streaming.infrastructure.usuario.controller;

import com.fiap.tech_challenge_web_streaming.domain.usuario.entity.Usuario;
import com.fiap.tech_challenge_web_streaming.infrastructure.usuario.dto.UsuarioPublicData;
import com.fiap.tech_challenge_web_streaming.infrastructure.usuario.dto.UsuarioUpdateData;
import com.fiap.tech_challenge_web_streaming.usecase.usuario.AtualizarUsuarioUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

public class AtualizarUsuarioControllerTest {

    @Test
    public void testAtualizarUsuario() {
        AtualizarUsuarioUseCase atualizarUsuarioUseCase = mock(AtualizarUsuarioUseCase.class);
        AtualizarUsuarioController controller = new AtualizarUsuarioController(atualizarUsuarioUseCase);

        UsuarioUpdateData updateData = new UsuarioUpdateData("nome", "email", null, null );

        Usuario usuario = new Usuario();
        usuario.setId("id");
        usuario.setNome("nome");

        when(atualizarUsuarioUseCase.execute(anyString(), any())).thenReturn(Mono.just(usuario));

        Mono<ResponseEntity<UsuarioPublicData>> response = controller.atualizarUsuario("id", updateData);

        StepVerifier.create(response)
                .assertNext(res -> {
                    verify(atualizarUsuarioUseCase).execute("id", updateData);
                    assert res.getStatusCode() == HttpStatus.OK;
                })
                .verifyComplete();
    }
}
