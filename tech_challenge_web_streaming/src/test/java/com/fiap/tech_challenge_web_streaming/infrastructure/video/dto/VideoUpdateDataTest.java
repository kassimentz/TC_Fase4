package com.fiap.tech_challenge_web_streaming.infrastructure.video.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VideoUpdateDataTest {

    @Test
    public void testVideoUpdateData() {
        VideoUpdateData videoUpdateData = new VideoUpdateData("Titulo", "Descricao", "Pets", LocalDate.now());

        assertEquals("Titulo", videoUpdateData.titulo());
        assertEquals("Descricao", videoUpdateData.descricao());
        assertEquals("Pets", videoUpdateData.categoria());
        assertEquals(LocalDate.now(), videoUpdateData.dataPublicacao());
    }
}
