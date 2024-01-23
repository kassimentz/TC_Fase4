package com.fiap.tech_challenge_web_streaming.infrastructure.usuario.dto;

import com.fiap.tech_challenge_web_streaming.domain.usuario.entity.Usuario;
import com.fiap.tech_challenge_web_streaming.domain.video.entity.Video;
import com.fiap.tech_challenge_web_streaming.infrastructure.usuario.dto.UsuarioPublicData;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsuarioPublicDataTest {

    @Test
    public void testFields() {
        String expectedId = "testId";
        String expectedNome = "testNome";
        String expectedEmail = "testEmail";
        List<Video> expectedFavoritos = new ArrayList<>();
        List<Video> expectedRecomendados = new ArrayList<>();

        UsuarioPublicData publicData = new UsuarioPublicData(expectedId, expectedNome, expectedEmail, expectedFavoritos, expectedRecomendados);

        assertEquals(expectedId, publicData.id());
        assertEquals(expectedNome, publicData.nome());
        assertEquals(expectedEmail, publicData.email());
        assertEquals(expectedFavoritos, publicData.favoritos());
        assertEquals(expectedRecomendados, publicData.recomendados());
    }
}
