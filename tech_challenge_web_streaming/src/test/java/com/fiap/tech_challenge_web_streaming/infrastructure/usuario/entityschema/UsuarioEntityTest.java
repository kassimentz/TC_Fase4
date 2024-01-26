package com.fiap.tech_challenge_web_streaming.infrastructure.usuario.entityschema;

import com.fiap.tech_challenge_web_streaming.domain.usuario.entity.Usuario;
import com.fiap.tech_challenge_web_streaming.domain.video.entity.Video;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsuarioEntityTest {

    @Test
    public void testFields() {
        String expectedId = "testId";
        String expectedNome = "testNome";
        String expectedEmail = "testEmail";
        List<Video> expectedFavoritos = new ArrayList<>();
        List<Video> expectedRecomendados = new ArrayList<>();

        UsuarioEntity usuarioEntity = new UsuarioEntity(expectedId, expectedNome, expectedEmail, expectedFavoritos, expectedRecomendados);

        assertEquals(expectedId, usuarioEntity.getId());
        assertEquals(expectedNome, usuarioEntity.getNome());
        assertEquals(expectedEmail, usuarioEntity.getEmail());
        assertEquals(expectedFavoritos, usuarioEntity.getFavoritos());
        assertEquals(expectedRecomendados, usuarioEntity.getRecomendados());
    }

    @Test
    public void testAddFavorito() {
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        Video video = new Video();
        usuarioEntity.addFavorito(video);

        assertEquals(1, usuarioEntity.getFavoritos().size());
        assertEquals(video, usuarioEntity.getFavoritos().get(0));
    }

    @Test
    public void testToUsuario() {
        String expectedId = "testId";
        String expectedNome = "testNome";
        String expectedEmail = "testEmail";
        List<Video> expectedFavoritos = new ArrayList<>();
        List<Video> expectedRecomendados = new ArrayList<>();

        UsuarioEntity usuarioEntity = new UsuarioEntity(expectedId, expectedNome, expectedEmail, expectedFavoritos, expectedRecomendados);
        Usuario usuario = usuarioEntity.toUsuario();

        assertEquals(expectedId, usuario.getId());
        assertEquals(expectedNome, usuario.getNome());
        assertEquals(expectedEmail, usuario.getEmail());
        assertEquals(expectedFavoritos, usuario.getFavoritos());
        assertEquals(expectedRecomendados, usuario.getRecomendados());
    }
}
