package com.fiap.tech_challenge_web_streaming.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VideoTest {

    private Video video;
    private String id = "1";
    private String titulo = "Test Title";
    private String descricao = "Test Description";
    private String url = "http://test.com";
    private LocalDate dataPublicacao = LocalDate.now();
    private Categoria categoria = Categoria.PETS;
    private List<String> favoritadoPorUsuarios = Arrays.asList("user1", "user2");

    @BeforeEach
    public void setUp() {
        video = new Video();
    }

    @Test
    public void testId() {
        video.setId(id);
        assertEquals(id, video.getId());
    }

    @Test
    public void testTitulo() {
        video.setTitulo(titulo);
        assertEquals(titulo, video.getTitulo());
    }

    @Test
    public void testDescricao() {
        video.setDescricao(descricao);
        assertEquals(descricao, video.getDescricao());
    }

    @Test
    public void testUrl() {
        video.setUrl(url);
        assertEquals(url, video.getUrl());
    }

    @Test
    public void testDataPublicacao() {
        video.setDataPublicacao(dataPublicacao);
        assertEquals(dataPublicacao, video.getDataPublicacao());
    }

    @Test
    public void testCategoria() {
        video.setCategoria(categoria);
        assertEquals(categoria, video.getCategoria());
    }

    @Test
    public void testFavoritadoPorUsuarios() {
        video.setFavoritadoPorUsuarios(favoritadoPorUsuarios);
        assertEquals(favoritadoPorUsuarios, video.getFavoritadoPorUsuarios());
    }

    @Test
    public void testAddFavoritadoPorUsuarios() {
        String usuarioId = "user3";
        video.addFavoritadoPorUsuarios(usuarioId);
        assertEquals(1, video.getFavoritadoPorUsuarios().size());
        assertEquals(usuarioId, video.getFavoritadoPorUsuarios().get(0));
    }
}
