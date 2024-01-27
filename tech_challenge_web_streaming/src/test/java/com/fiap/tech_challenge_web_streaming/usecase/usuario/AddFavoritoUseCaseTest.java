package com.fiap.tech_challenge_web_streaming.usecase.usuario;

import com.fiap.tech_challenge_web_streaming.domain.usuario.entity.Usuario;
import com.fiap.tech_challenge_web_streaming.domain.usuario.gateway.UsuarioGateway;
import com.fiap.tech_challenge_web_streaming.domain.video.entity.Video;
import com.fiap.tech_challenge_web_streaming.domain.video.gateway.VideoGateway;
import com.fiap.tech_challenge_web_streaming.usecase.usuario.dto.IUsuarioAddFavoritoRequestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AddFavoritoUseCaseTest {

    @Mock
    private UsuarioGateway usuarioGateway;

    @Mock
    private VideoGateway videoGateway;

    private AddFavoritoUseCase addFavoritoUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        addFavoritoUseCase = new AddFavoritoUseCase(usuarioGateway, videoGateway);
    }

    @Test
    void testExecute() {
        Usuario usuario = new Usuario();
        usuario.setId("1");

        Video video = new Video();
        video.setId("1");
        usuario.addFavorito(video);
        when(usuarioGateway.buscarPorId(any(String.class))).thenReturn(Mono.just(usuario));
        when(videoGateway.buscarPorId(any(String.class))).thenReturn(Mono.just(video));
        when(usuarioGateway.atualizar(any(Usuario.class))).thenReturn(Mono.just(usuario));
        when(videoGateway.atualizar(any(Video.class))).thenReturn(Mono.just(video));

        IUsuarioAddFavoritoRequestData videoRequest = () -> "1";


        Mono<Usuario> result = addFavoritoUseCase.execute("1", videoRequest);

        Usuario resultUsuario = result.block();

        Assertions.assertNotNull(resultUsuario);
        Assertions.assertEquals(usuario.getId(), resultUsuario.getId());
    }
}
