package com.fiap.tech_challenge_web_streaming.domain.categoria.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoriaTest {

    @Test
    public void testCategoriaEnum() {
        assertEquals("engraçado", Categoria.ENGRACADO.getCategoria());
        assertEquals("tecnologia", Categoria.TECNOLOGIA.getCategoria());
        assertEquals("pets", Categoria.PETS.getCategoria());
    }
}
