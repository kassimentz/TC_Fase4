package com.fiap.tech_challenge_web_streaming.usecase.usuario;

import com.fiap.tech_challenge_web_streaming.domain.categoria.entity.Categoria;
import com.fiap.tech_challenge_web_streaming.domain.usuario.entity.Usuario;
import com.fiap.tech_challenge_web_streaming.domain.usuario.gateway.UsuarioGateway;
import com.fiap.tech_challenge_web_streaming.domain.video.entity.Video;
import com.fiap.tech_challenge_web_streaming.domain.video.gateway.VideoGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class BuscarRecomendacoesUseCaseTest {

    @Mock
    private UsuarioGateway usuarioGateway;

    @Mock
    private VideoGateway videoGateway;

    private BuscarRecomendacoesUseCase buscarRecomendacoesUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        buscarRecomendacoesUseCase = new BuscarRecomendacoesUseCase(usuarioGateway, videoGateway);
    }

    @Test
    void testExecute() {
        Usuario usuario = new Usuario();
        usuario.setId("1");
        Video video = new Video();
        video.setId("1");
        video.setCategoria(Categoria.PETS);
        usuario.setFavoritos(Collections.singletonList(video));

        when(usuarioGateway.buscarPorId(anyString())).thenReturn(Mono.just(usuario));
        when(videoGateway.buscarTodos()).thenReturn(Flux.just(video));
        when(videoGateway.count()).thenReturn(Mono.just(1L));

        Flux<Video> result = buscarRecomendacoesUseCase.execute("1");
        Assertions.assertNotNull(result);
    }
}
