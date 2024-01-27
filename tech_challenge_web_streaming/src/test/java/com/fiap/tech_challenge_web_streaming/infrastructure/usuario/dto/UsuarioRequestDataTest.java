package com.fiap.tech_challenge_web_streaming.infrastructure.usuario.dto;

import com.fiap.tech_challenge_web_streaming.domain.video.entity.Video;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

 class UsuarioRequestDataTest {

    @Test
     void testFields() {
        String expectedNome = "testNome";
        String expectedEmail = "testEmail";
        List<Video> expectedFavoritos = new ArrayList<>();
        List<Video> expectedRecomendados = new ArrayList<>();

        UsuarioRequestData requestData = new UsuarioRequestData(expectedNome, expectedEmail);

        assertEquals(expectedNome, requestData.nome());
        assertEquals(expectedEmail, requestData.email());
    }
}
