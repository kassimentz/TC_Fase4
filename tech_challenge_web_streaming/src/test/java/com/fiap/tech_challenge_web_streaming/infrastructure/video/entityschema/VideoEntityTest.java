package com.fiap.tech_challenge_web_streaming.infrastructure.video.entityschema;

import com.fiap.tech_challenge_web_streaming.domain.categoria.entity.Categoria;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class VideoEntityTest {

    @Test
    public void testVideoEntity() {
        VideoEntity videoEntity = new VideoEntity();
        videoEntity.setId("1");
        videoEntity.setTitulo("Titulo");
        videoEntity.setDescricao("Descricao");
        videoEntity.setUrl("Url");
        videoEntity.setDataPublicacao(LocalDate.now());
        videoEntity.setCategoria(Categoria.PETS);

        assertEquals("1", videoEntity.getId());
        assertEquals("Titulo", videoEntity.getTitulo());
        assertEquals("Descricao", videoEntity.getDescricao());
        assertEquals("Url", videoEntity.getUrl());
        assertEquals(LocalDate.now(), videoEntity.getDataPublicacao());
        assertEquals(Categoria.PETS, videoEntity.getCategoria());

        VideoEntity videoEntity2 = new VideoEntity();
        videoEntity2.setId("2");

        assertNotEquals(videoEntity, videoEntity2);
        assertNotEquals(videoEntity.hashCode(), videoEntity2.hashCode());
    }
}
