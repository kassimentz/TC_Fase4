package com.fiap.tech_challenge_web_streaming.usecases;

import com.fiap.tech_challenge_web_streaming.controllers.dto.usuario.UsuarioResponseDTO;
import com.fiap.tech_challenge_web_streaming.entities.Usuario;
import com.fiap.tech_challenge_web_streaming.entities.Video;
import com.fiap.tech_challenge_web_streaming.interfaces.UsuarioRepositoryInterface;
import com.fiap.tech_challenge_web_streaming.interfaces.VideoRepositoryInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UsuarioUCTest {

    @InjectMocks
    private UsuarioUC usuarioUC;

    @Mock
    private UsuarioRepositoryInterface usuarioRepository;

    @Mock
    private VideoRepositoryInterface videoRepository;

    private Usuario usuario;
    private Video video;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        usuario = new Usuario();
        usuario.setId("1234");
        usuario.setNome("Test Name");
        usuario.setEmail("test@email.com");

        video = new Video();
        video.setId("5678");
        video.setTitulo("Test Title");
        video.setDescricao("Test Description");
        video.setUrl("http://test.com");
    }

    @Test
    public void testAddVideoFavorito() {
        when(usuarioRepository.findById(usuario.getId())).thenReturn(Mono.just(usuario));
        when(videoRepository.findById(video.getId())).thenReturn(Mono.just(video));
        when(usuarioRepository.save(usuario)).thenReturn(Mono.just(usuario));
        when(videoRepository.save(video)).thenReturn(Mono.just(video));

        Mono<UsuarioResponseDTO> result = usuarioUC.addVideoFavorito(usuario.getId(), video.getId());

        UsuarioResponseDTO expectedResponse = new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getVideosFavoritados(),
                usuario.getVideosRecomendados()
        );

        assertEquals(expectedResponse, result.block());
    }

    @Test
    public void testGetRecomendacoes() {
        when(usuarioRepository.findById(usuario.getId())).thenReturn(Mono.just(usuario));
        when(videoRepository.findAll()).thenReturn(Flux.just(video));

        Flux<Video> result = usuarioUC.getRecomendacoes(usuario.getId());

        List<Video> expectedVideos = new ArrayList<>();
        expectedVideos.add(video);

        assertEquals(expectedVideos, result.collectList().block());
    }
}
