package com.fiap.tech_challenge_web_streaming.domain.video.factories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PageDataTest {

    private PageData pageData;
    private PageData.PageBuilder pageBuilder;

    @BeforeEach
    public void setUp() {
        pageBuilder = PageData.newPageBuilder()
                .page(1)
                .size(10)
                .direcao("ASC")
                .ordenacao("title");
        pageData = pageBuilder.build();
    }

    @Test
    public void testGetPage() {
        assertEquals(1, pageData.getPage());
    }

    @Test
    public void testGetSize() {
        assertEquals(10, pageData.getSize());
    }

    @Test
    public void testGetDirecao() {
        assertEquals("ASC", pageData.getDirecao());
    }

    @Test
    public void testGetOrdenacao() {
        assertEquals("title", pageData.getOrdenacao());
    }

    @Test
    public void testPageBuilderGetPage() {
        assertEquals(1, pageBuilder.build().getPage());
    }

    @Test
    public void testPageBuilderGetSize() {
        assertEquals(10, pageBuilder.build().getSize());
    }

    @Test
    public void testPageBuilderGetDirecao() {
        assertEquals("ASC", pageBuilder.build().getDirecao());
    }

    @Test
    public void testPageBuilderGetOrdenacao() {
        assertEquals("title", pageBuilder.build().getOrdenacao());
    }
}
