package com.fiap.tech_challenge_web_streaming.domain.video.entity;

import com.fiap.tech_challenge_web_streaming.domain.categoria.entity.Categoria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

 class VideoTest {

    @Mock
    private Categoria categoria;

    private Video video;

    @BeforeEach
     void setUp() {
        MockitoAnnotations.openMocks(this);
        List<String> usuariosQueFavoritaram = new ArrayList<>();
        usuariosQueFavoritaram.add("1");
        video = new Video("1", "Test Video", "This is a test video", "www.test.com", LocalDate.now(), categoria);
        video.setUsuariosQueFavoritaram(usuariosQueFavoritaram);
    }

    @Test
     void testGetId() {
        assertEquals("1", video.getId());
    }

    @Test
     void testGetTitulo() {
        assertEquals("Test Video", video.getTitulo());
    }

    @Test
     void testGetDescricao() {
        assertEquals("This is a test video", video.getDescricao());
    }

    @Test
     void testGetUrl() {
        assertEquals("www.test.com", video.getUrl());
    }

    @Test
     void testGetCategoria() {
        when(categoria.getCategoria()).thenReturn("Test Category");
        assertEquals("Test Category", video.getCategoria().getCategoria());
    }

    @Test
     void testGetUsuariosQueFavoritaram() {
        assertEquals("1", video.getUsuariosQueFavoritaram().get(0));
    }

    @Test
     void testFavoritarVideoPorUsuario() {
        video.favoritarVideoPorUsuario("2");
        assertEquals("2", video.getUsuariosQueFavoritaram().get(1));
    }
}
