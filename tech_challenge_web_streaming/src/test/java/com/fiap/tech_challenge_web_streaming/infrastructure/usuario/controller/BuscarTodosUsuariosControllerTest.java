package com.fiap.tech_challenge_web_streaming.infrastructure.usuario.controller;

import com.fiap.tech_challenge_web_streaming.domain.usuario.entity.Usuario;
import com.fiap.tech_challenge_web_streaming.infrastructure.usuario.dto.UsuarioPublicData;
import com.fiap.tech_challenge_web_streaming.usecase.usuario.BuscarTodosUsuariosUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

class BuscarTodosUsuariosControllerTest {

    @Test
    void testBuscarTodosUsuarios() {
        BuscarTodosUsuariosUseCase buscarTodosUsuariosUseCase = mock(BuscarTodosUsuariosUseCase.class);
        BuscarTodosUsuariosController controller = new BuscarTodosUsuariosController(buscarTodosUsuariosUseCase);

        Usuario usuario = new Usuario();
        usuario.setId("id");
        usuario.setNome("nome");

        when(buscarTodosUsuariosUseCase.execute()).thenReturn(Flux.just(usuario));

        ResponseEntity<Flux<UsuarioPublicData>> response = controller.buscarTodosUsuarios();

        StepVerifier.create(response.getBody())
                .assertNext(usuarioPublicData -> {
                    verify(buscarTodosUsuariosUseCase).execute();
                    assert usuarioPublicData.id().equals(usuario.getId());
                    assert usuarioPublicData.nome().equals(usuario.getNome());
                    assert response.getStatusCode() == HttpStatus.OK;
                })
                .verifyComplete();
    }
}
