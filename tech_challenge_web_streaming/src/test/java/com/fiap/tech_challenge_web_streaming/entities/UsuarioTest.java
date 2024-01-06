package com.fiap.tech_challenge_web_streaming.entities;

import com.fiap.tech_challenge_web_streaming.controllers.dto.usuario.UsuarioRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class UsuarioTest {
    private Usuario usuario;
    private String id = "1";
    private String nome = "Test";
    private String email = "test@test.com";
    private List<Video> favoritos = Arrays.asList(new Video(), new Video());
    private List<Video> recomendados = Arrays.asList(new Video(), new Video());

    @BeforeEach
    public void setUp() {
        usuario = new Usuario();
    }

    @Test
    public void testId() {
        usuario.setId(id);
        assertEquals(id, usuario.getId());
    }

    @Test
    public void testNome() {
        usuario.setNome(nome);
        assertEquals(nome, usuario.getNome());
    }

    @Test
    public void testEmail() {
        usuario.setEmail(email);
        assertEquals(email, usuario.getEmail());
    }

    @Test
    public void testFavoritos() {
        usuario.setFavoritos(favoritos);
        assertEquals(favoritos, usuario.getFavoritos());
    }

    @Test
    public void testRecomendados() {
        usuario.setRecomendados(recomendados);
        assertEquals(recomendados, usuario.getRecomendados());
    }

    @Test
    public void testConstructorWithParameters() {
        Usuario usuario = new Usuario(id, nome, email, favoritos, recomendados);
        assertEquals(nome, usuario.getNome());
        assertEquals(email, usuario.getEmail());
        assertEquals(favoritos, usuario.getFavoritos());
        assertEquals(recomendados, usuario.getRecomendados());
    }

    @Test
    public void testConstructorWithUsuarioRequestDTO() {
        UsuarioRequestDTO requestDTO = new UsuarioRequestDTO();
        requestDTO.setNome(nome);
        requestDTO.setEmail(email);
        Usuario usuario = new Usuario(requestDTO);
        assertEquals(nome, usuario.getNome());
        assertEquals(email, usuario.getEmail());
    }
}
