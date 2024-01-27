package com.fiap.tech_challenge_web_streaming.infrastructure.usuario.dto;

import com.fiap.tech_challenge_web_streaming.domain.usuario.entity.Usuario;
import com.fiap.tech_challenge_web_streaming.domain.video.entity.Video;
import com.fiap.tech_challenge_web_streaming.infrastructure.usuario.dto.UsuarioPublicData;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

 class UsuarioPublicDataTest {

    @Test
     void testFields() {
        String expectedId = "testId";
        String expectedNome = "testNome";
        String expectedEmail = "testEmail";
        List<Video> expectedFavoritos = new ArrayList<>();
        List<Video> expectedRecomendados = new ArrayList<>();

        UsuarioPublicData Data = new UsuarioPublicData(expectedId, expectedNome, expectedEmail, expectedFavoritos, expectedRecomendados);

        assertEquals(expectedId, Data.id());
        assertEquals(expectedNome, Data.nome());
        assertEquals(expectedEmail, Data.email());
        assertEquals(expectedFavoritos, Data.favoritos());
        assertEquals(expectedRecomendados, Data.recomendados());
    }
}
