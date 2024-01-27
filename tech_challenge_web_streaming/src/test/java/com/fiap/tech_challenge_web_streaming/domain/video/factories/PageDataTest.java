package com.fiap.tech_challenge_web_streaming.domain.video.factories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

 class PageDataTest {

    private PageData pageData;
    private PageData.PageBuilder pageBuilder;

    @BeforeEach
     void setUp() {
        pageBuilder = PageData.newPageBuilder()
                .page(1)
                .size(10)
                .direcao("ASC")
                .ordenacao("title");
        pageData = pageBuilder.build();
    }

    @Test
     void testGetPage() {
        assertEquals(1, pageData.getPage());
    }

    @Test
     void testGetSize() {
        assertEquals(10, pageData.getSize());
    }

    @Test
     void testGetDirecao() {
        assertEquals("ASC", pageData.getDirecao());
    }

    @Test
     void testGetOrdenacao() {
        assertEquals("title", pageData.getOrdenacao());
    }

    @Test
     void testPageBuilderGetPage() {
        assertEquals(1, pageBuilder.build().getPage());
    }

    @Test
     void testPageBuilderGetSize() {
        assertEquals(10, pageBuilder.build().getSize());
    }

    @Test
     void testPageBuilderGetDirecao() {
        assertEquals("ASC", pageBuilder.build().getDirecao());
    }

    @Test
     void testPageBuilderGetOrdenacao() {
        assertEquals("title", pageBuilder.build().getOrdenacao());
    }
}
