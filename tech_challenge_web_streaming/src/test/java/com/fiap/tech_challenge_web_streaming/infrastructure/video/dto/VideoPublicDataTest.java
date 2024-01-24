package com.fiap.tech_challenge_web_streaming.infrastructure.video.dto;

import com.fiap.tech_challenge_web_streaming.domain.categoria.entity.Categoria;
import com.fiap.tech_challenge_web_streaming.domain.video.entity.Video;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VideoPublicDataTest {

    @Test
    public void testVideoPublicData() {
        Video video = new Video("Titulo", "Descricao",  LocalDate.now(), Categoria.PETS);
        VideoPublicData videoPublicData = new VideoPublicData(video);

        assertEquals(video.getId(), videoPublicData.id());
        assertEquals(video.getTitulo(), videoPublicData.titulo());
        assertEquals(video.getDescricao(), videoPublicData.descricao());
        assertEquals(video.getCategoria().toString(), videoPublicData.categoria());
        assertEquals(video.getDataPublicacao(), videoPublicData.dataPublicacao());
    }
}
