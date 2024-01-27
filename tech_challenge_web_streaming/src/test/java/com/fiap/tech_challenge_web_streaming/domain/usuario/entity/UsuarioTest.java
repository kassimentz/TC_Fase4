package com.fiap.tech_challenge_web_streaming.domain.usuario.entity;

import com.fiap.tech_challenge_web_streaming.domain.video.entity.Video;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

 class UsuarioTest {

    @Mock
    private Video video;

    private Usuario usuario;

    @BeforeEach
     void setUp() {
        MockitoAnnotations.openMocks(this);
        List<Video> favoritos = new ArrayList<>();
        favoritos.add(video);
        usuario = new Usuario("1", "Test User", "testuser@gmail.com", favoritos, new ArrayList<>());
    }

    @Test
     void testGetId() {
        assertEquals("1", usuario.getId());
    }

    @Test
     void testGetNome() {
        assertEquals("Test User", usuario.getNome());
    }

    @Test
     void testGetEmail() {
        assertEquals("testuser@gmail.com", usuario.getEmail());
    }

    @Test
     void testGetFavoritos() {
        when(video.getId()).thenReturn("1");
        assertEquals("1", usuario.getFavoritos().get(0).getId());
    }

    @Test
     void testGetRecomendados() {
        assertEquals(0, usuario.getRecomendados().size());
    }
}
