package com.fiap.tech_challenge_web_streaming.entities;

import com.fiap.tech_challenge_web_streaming.controllers.dto.usuario.UsuarioRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class UsuarioTest {
    private Usuario usuario;
    private String id = "1";
    private String nome = "Test";
    private String email = "test@test.com";
    private List<Video> favoritos = Arrays.asList(new Video(), new Video());
    private List<Video> recomendados = Arrays.asList(new Video(), new Video());

    @BeforeEach
    public void setUp() {
        usuario = new Usuario();
    }

    @Test
    public void testId() {
        usuario.setId(id);
        assertEquals(id, usuario.getId());
    }

    @Test
    public void testNome() {
        usuario.setNome(nome);
        assertEquals(nome, usuario.getNome());
    }

    @Test
    public void testEmail() {
        usuario.setEmail(email);
        assertEquals(email, usuario.getEmail());
    }

    @Test
    public void testFavoritos() {
        usuario.setVideosFavoritados(favoritos);
        assertEquals(favoritos, usuario.getVideosFavoritados());
    }

    @Test
    public void testRecomendados() {
        usuario.setVideosRecomendados(recomendados);
        assertEquals(recomendados, usuario.getVideosRecomendados());
    }

    @Test
    public void testConstructorWithParameters() {
        Usuario usuario = new Usuario(id, nome, email, favoritos, recomendados);
        assertEquals(nome, usuario.getNome());
        assertEquals(email, usuario.getEmail());
        assertEquals(favoritos, usuario.getVideosFavoritados());
        assertEquals(recomendados, usuario.getVideosRecomendados());
    }

    @Test
    public void testFavoritarVideo() {
        Video video = new Video();
        usuario.favoritarVideo(video);
        assertEquals(1, usuario.getVideosFavoritados().size());
        assertEquals(video, usuario.getVideosFavoritados().get(0));
    }

    @Test
    public void testAddVideoRecomendado() {
        Video video = new Video();
        usuario.addVideoRecomendado(video);
        assertEquals(1, usuario.getVideosRecomendados().size());
        assertEquals(video, usuario.getVideosRecomendados().get(0));
    }
}
