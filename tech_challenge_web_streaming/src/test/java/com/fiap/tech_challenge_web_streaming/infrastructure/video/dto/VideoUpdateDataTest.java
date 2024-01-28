package com.fiap.tech_challenge_web_streaming.infrastructure.video.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

 class VideoUpdateDataTest {

    @Test
     void testVideoUpdateData() {
        VideoRequestData videoUpdateData = new VideoRequestData("Titulo", "Descricao", "Pets");

        assertEquals("Titulo", videoUpdateData.titulo());
        assertEquals("Descricao", videoUpdateData.descricao());
        assertEquals("Pets", videoUpdateData.categoria());

    }
}
