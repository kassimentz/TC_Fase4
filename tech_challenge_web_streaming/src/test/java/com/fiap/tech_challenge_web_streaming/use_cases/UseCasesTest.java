package com.fiap.tech_challenge_web_streaming.use_cases;

import com.fiap.tech_challenge_web_streaming.controllers.dto.usuario.UsuarioResponseDTO;
import com.fiap.tech_challenge_web_streaming.entities.Categoria;
import com.fiap.tech_challenge_web_streaming.usecases.UseCases;
import com.fiap.tech_challenge_web_streaming.entities.Usuario;
import com.fiap.tech_challenge_web_streaming.entities.Video;
import com.fiap.tech_challenge_web_streaming.interfaces.UsuarioRepositoryInterface;
import com.fiap.tech_challenge_web_streaming.interfaces.VideoRepositoryInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UseCasesTest {

    @InjectMocks
    private UseCases useCases;

    @Mock
    private UsuarioRepositoryInterface usuarioRepository;

    @Mock
    private VideoRepositoryInterface videoRepository;

    private Usuario usuario;
    private Video video;
    private List<Video> videos;


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
        video.setCategoria(Categoria.TECNOLOGIA);

        usuario = new Usuario();
        usuario.setId("1234");
        usuario.setNome("Test Name");
        usuario.setEmail("test@email.com");

        Video video1 = new Video();
        video1.setId("5678");
        video1.setTitulo("Test Title 1");
        video1.setDescricao("Test Description 1");
        video1.setUrl("http://test1.com");
        video1.setCategoria(Categoria.PETS);

        Video video2 = new Video();
        video2.setId("5679");
        video2.setTitulo("Test Title 2");
        video2.setDescricao("Test Description 2");
        video2.setUrl("http://test2.com");
        video2.setCategoria(Categoria.ENGRACADO);

        videos = Arrays.asList(video1, video2, video);
    }

    @Test
    public void testAddVideoFavorito() {
        when(usuarioRepository.findById(usuario.getId())).thenReturn(Mono.just(usuario));
        when(videoRepository.findById(video.getId())).thenReturn(Mono.just(video));
        when(usuarioRepository.save(usuario)).thenReturn(Mono.just(usuario));
        when(videoRepository.save(video)).thenReturn(Mono.just(video));

        Mono<UsuarioResponseDTO> result = useCases.addVideoFavorito(usuario.getId(), video.getId());

        StepVerifier.create(result)
                .assertNext(usuarioResponseDTO -> {
                    assertEquals(usuario.getId(), usuarioResponseDTO.getId());
                    assertEquals(usuario.getNome(), usuarioResponseDTO.getNome());
                    assertEquals(usuario.getEmail(), usuarioResponseDTO.getEmail());
                    assertEquals(usuario.getVideosFavoritados(), usuarioResponseDTO.getFavoritos());
                    assertEquals(usuario.getVideosRecomendados(), usuarioResponseDTO.getRecomendados());
                })
                .verifyComplete();

        verify(usuarioRepository, times(1)).findById(usuario.getId());
        verify(videoRepository, times(1)).findById(video.getId());
        verify(usuarioRepository, times(1)).save(usuario);
        verify(videoRepository, times(1)).save(video);
    }

    @Test
    public void testGetRecomendacoes() {
        Usuario usuario = new Usuario();
        usuario.setId("1234");
        usuario.setNome("Test Name");
        usuario.setEmail("test@email.com");

        Video video1 = new Video();
        video1.setId("5678");
        video1.setTitulo("Test Title 1");
        video1.setDescricao("Test Description 1");
        video1.setUrl("http://test1.com");
        video1.setCategoria(Categoria.PETS);
        usuario.favoritarVideo(video1);

        Video video2 = new Video();
        video2.setId("5679");
        video2.setTitulo("Test Title 2");
        video2.setDescricao("Test Description 2");
        video2.setUrl("http://test2.com");
        video2.setCategoria(Categoria.ENGRACADO);
        video2.addFavoritadoPorUsuarios(usuario.getId());

        List<Video> videos = Arrays.asList(video1, video2);

        when(usuarioRepository.findById(usuario.getId())).thenReturn(Mono.just(usuario));
        when(videoRepository.findAll()).thenReturn(Flux.fromIterable(videos));
        when(videoRepository.count()).thenReturn(Mono.just((long) videos.size()));

        Flux<Video> result = useCases.getRecomendacoes(usuario.getId());

        StepVerifier.create(result)
                .expectNextCount(videos.size())
                .verifyComplete();

        verify(usuarioRepository, times(1)).findById(usuario.getId());
        verify(videoRepository, times(1)).findAll();
        verify(videoRepository, times(1)).count();
    }
}
