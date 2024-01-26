package com.fiap.tech_challenge_web_streaming.infrastructure.usuario.controller;

import com.fiap.tech_challenge_web_streaming.domain.usuario.entity.Usuario;
import com.fiap.tech_challenge_web_streaming.infrastructure.usuario.dto.UsuarioPublicData;
import com.fiap.tech_challenge_web_streaming.infrastructure.usuario.dto.UsuarioRequestData;
import com.fiap.tech_challenge_web_streaming.usecase.usuario.CriarUsuarioUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

public class CriarUsuarioControllerTest {

    @Test
    public void testCriarUsuario() {
        CriarUsuarioUseCase criarUsuarioUseCase = mock(CriarUsuarioUseCase.class);
        CriarUsuarioController controller = new CriarUsuarioController(criarUsuarioUseCase);

        UsuarioRequestData requestData = new UsuarioRequestData("nome", "email", null, null);

        Usuario usuario = new Usuario();
        usuario.setId("id");
        usuario.setNome("nome");

        when(criarUsuarioUseCase.executar(any())).thenReturn(Mono.just(usuario));

        Mono<ResponseEntity<UsuarioPublicData>> response = controller.criarUsuario(requestData);

        StepVerifier.create(response)
                .assertNext(res -> {
                    verify(criarUsuarioUseCase).executar(requestData);
                    assert res.getBody().id().equals(usuario.getId());
                    assert res.getBody().nome().equals(usuario.getNome());
                    assert res.getStatusCode() == HttpStatus.CREATED;
                })
                .verifyComplete();
    }
}
