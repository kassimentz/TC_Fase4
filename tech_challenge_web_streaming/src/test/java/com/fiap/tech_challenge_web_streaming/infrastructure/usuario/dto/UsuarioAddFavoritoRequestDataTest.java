package com.fiap.tech_challenge_web_streaming.infrastructure.usuario.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

 class UsuarioAddFavoritoRequestDataTest {

    @Test
     void testVideoId() {
        String expectedVideoId = "testVideoId";
        UsuarioAddFavoritoRequestData requestData = new UsuarioAddFavoritoRequestData(expectedVideoId);
        assertEquals(expectedVideoId, requestData.videoId());
    }
}
