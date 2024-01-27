package com.fiap.tech_challenge_web_streaming.domain.video.factories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CriteriosBuscaVideoTest {

    private CriteriosBuscaVideo criteriosBuscaVideo;

    @BeforeEach
    public void setUp() {
        criteriosBuscaVideo = CriteriosBuscaVideo.newBuilder()
                .titulo("Test Video")
                .dataPublicacao(LocalDate.now())
                .categoria("Test Category")
                .build();
    }

    @Test
    void testGetTitulo() {
        assertEquals("Test Video", criteriosBuscaVideo.getTitulo());
    }

    @Test
    void testGetDataPublicacao() {
        assertEquals(LocalDate.now(), criteriosBuscaVideo.getDataPublicacao());
    }

    @Test
    void testGetCategoria() {
        assertEquals("Test Category", criteriosBuscaVideo.getCategoria());
    }
}
