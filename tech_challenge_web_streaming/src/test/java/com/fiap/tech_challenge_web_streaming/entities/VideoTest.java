package com.fiap.tech_challenge_web_streaming.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VideoTest {

    private Video video;

    @BeforeEach
    public void setUp() {
        video = new Video();
    }

    @Test
    public void testId() {
        String id = "1234";
        video.setId(id);
        assertEquals(id, video.getId());
    }

    @Test
    public void testTitulo() {
        String titulo = "Test Title";
        video.setTitulo(titulo);
        assertEquals(titulo, video.getTitulo());
    }

    @Test
    public void testDescricao() {
        String descricao = "Test Description";
        video.setDescricao(descricao);
        assertEquals(descricao, video.getDescricao());
    }

    @Test
    public void testUrl() {
        String url = "http://test.com";
        video.setUrl(url);
        assertEquals(url, video.getUrl());
    }

    @Test
    public void testDataPublicacao() {
        LocalDate dataPublicacao = LocalDate.now();
        video.setDataPublicacao(dataPublicacao);
        assertEquals(dataPublicacao, video.getDataPublicacao());
    }

    @Test
    public void testCategorias() {
        List<Categoria> categorias = Arrays.asList(Categoria.PETS, Categoria.ENGRACADO);
        video.setCategorias(categorias);
        assertEquals(categorias, video.getCategorias());
    }
}
