package com.fiap.tech_challenge_web_streaming.infrastructure.usuario.controller;

import com.fiap.tech_challenge_web_streaming.domain.usuario.entity.Usuario;
import com.fiap.tech_challenge_web_streaming.infrastructure.usuario.dto.UsuarioAddFavoritoRequestData;
import com.fiap.tech_challenge_web_streaming.infrastructure.usuario.dto.UsuarioPublicData;
import com.fiap.tech_challenge_web_streaming.usecase.usuario.AddFavoritoUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

class AdicionarVideoFavoritoControllerTest {

    @Test
    void testAdicionarVideoFavorito() {
        AddFavoritoUseCase addFavoritoUseCase = mock(AddFavoritoUseCase.class);
        AdicionarVideoFavoritoController controller = new AdicionarVideoFavoritoController(addFavoritoUseCase);

        UsuarioAddFavoritoRequestData requestData = new UsuarioAddFavoritoRequestData("videoId");

        Usuario usuario = new Usuario();
        usuario.setId("id");
        usuario.setNome("nome");

        when(addFavoritoUseCase.execute(anyString(), any())).thenReturn(Mono.just(usuario));

        Mono<ResponseEntity<UsuarioPublicData>> response = controller.adicionarVideoFavorito("id", requestData);

        StepVerifier.create(response)
                .assertNext(res -> {
                    verify(addFavoritoUseCase).execute("id", requestData);
                    assert res.getStatusCode() == HttpStatus.OK;
                })
                .verifyComplete();
    }
}
