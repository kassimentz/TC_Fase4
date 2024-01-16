package com.fiap.tech_challenge_web_streaming.entities;

import com.fiap.tech_challenge_web_streaming.controllers.dto.usuario.UsuarioRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {
    private Usuario usuario;
    private String id = "1";
    private String nome = "Test";
    private String email = "test@test.com";
    private List<String> favoritos = Arrays.asList("a", "b");
    private List<String> recomendados = Arrays.asList("a", "b");

    @BeforeEach
    public void setUp() {
        usuario = new Usuario();
    }

    @Test
    void testId() {
        usuario.setId(id);
        assertEquals(id, usuario.getId());
    }

    @Test
    void testNome() {
        usuario.setNome(nome);
        assertEquals(nome, usuario.getNome());
    }

    @Test
    void testEmail() {
        usuario.setEmail(email);
        assertEquals(email, usuario.getEmail());
    }

    @Test
    void testFavoritos() {
        usuario.setVideosFavoritados(favoritos);
        assertEquals(favoritos, usuario.getVideosFavoritados());
    }

    @Test
    void testAddFavorito() {
        String video = "sadasd";
        usuario.favoritarVideo(video);
        assertTrue(usuario.getVideosFavoritados().contains(video));
    }

    @Test
    void testRecomendados() {
        usuario.setVideosRecomendados(recomendados);
        assertEquals(recomendados, usuario.getVideosRecomendados());
    }

    @Test
    void testAddRecomendado() {
        String video = "sadasd";
        usuario.addVideoRecomendado(video);
        assertTrue(usuario.getVideosRecomendados().contains(video));
    }

    @Test
    void testConstructorWithParameters() {
        Usuario usuario = new Usuario(id, nome, email, favoritos, recomendados);
        assertEquals(nome, usuario.getNome());
        assertEquals(email, usuario.getEmail());
        assertEquals(favoritos, usuario.getVideosFavoritados());
        assertEquals(recomendados, usuario.getVideosRecomendados());
    }

    @Test
    void testConstructorWithUsuarioRequestDTO() {
        UsuarioRequestDTO requestDTO = new UsuarioRequestDTO();
        requestDTO.setNome(nome);
        requestDTO.setEmail(email);
        Usuario usuario = new Usuario(requestDTO);
        assertEquals(nome, usuario.getNome());
        assertEquals(email, usuario.getEmail());
    }
}
