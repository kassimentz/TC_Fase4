package com.fiap.tech_challenge_web_streaming.infrastructure.usuario.controller;

import com.fiap.tech_challenge_web_streaming.domain.usuario.entity.Usuario;
import com.fiap.tech_challenge_web_streaming.infrastructure.usuario.dto.UsuarioPublicData;
import com.fiap.tech_challenge_web_streaming.usecase.usuario.BuscarUsuarioUseCase;
import com.fiap.tech_challenge_web_streaming.usecase.usuario.dto.IUsuarioPublicData;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

class BuscarUsuarioControllerTest {

    @Test
    void testBuscarUsuarioporId() {
        BuscarUsuarioUseCase buscarUsuarioUseCase = mock(BuscarUsuarioUseCase.class);
        BuscarUsuarioController controller = new BuscarUsuarioController(buscarUsuarioUseCase);

        Usuario usuario = new Usuario();
        usuario.setId("id");
        usuario.setNome("nome");

        when(buscarUsuarioUseCase.execute(anyString())).thenReturn(Mono.just(usuario));

        Mono<ResponseEntity<UsuarioPublicData>> response = controller.buscarUsuarioporId("id");

        StepVerifier.create(response)
                .assertNext(res -> {
                    verify(buscarUsuarioUseCase).execute("id");
                    assert res.getBody().id().equals(usuario.getId());
                    assert res.getBody().nome().equals(usuario.getNome());
                    assert res.getStatusCode() == HttpStatus.OK;
                })
                .verifyComplete();
    }
}
