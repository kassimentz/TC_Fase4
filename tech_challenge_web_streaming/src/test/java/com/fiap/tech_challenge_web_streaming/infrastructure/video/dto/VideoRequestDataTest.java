package com.fiap.tech_challenge_web_streaming.infrastructure.video.dto;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VideoRequestDataTest {

    @Test
    public void testVideoRequestData() {
        VideoRequestData videoRequestData = new VideoRequestData("Titulo", "Descricao", "Pets", LocalDate.now());

        assertEquals("Titulo", videoRequestData.titulo());
        assertEquals("Descricao", videoRequestData.descricao());
        assertEquals("Pets", videoRequestData.categoria());
        assertEquals(LocalDate.now(), videoRequestData.dataPublicacao());
    }
}
