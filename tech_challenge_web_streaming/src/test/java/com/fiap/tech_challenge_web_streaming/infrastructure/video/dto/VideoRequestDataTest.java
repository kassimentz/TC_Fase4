package com.fiap.tech_challenge_web_streaming.infrastructure.video.dto;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

 class VideoRequestDataTest {

    @Test
     void testVideoRequestData() {
        VideoRequestData videoRequestData = new VideoRequestData("Titulo", "Descricao", "Pets");

        assertEquals("Titulo", videoRequestData.titulo());
        assertEquals("Descricao", videoRequestData.descricao());
        assertEquals("Pets", videoRequestData.categoria());

    }
}
