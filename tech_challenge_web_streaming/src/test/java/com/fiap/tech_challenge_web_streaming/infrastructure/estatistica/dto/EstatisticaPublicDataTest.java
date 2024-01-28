package com.fiap.tech_challenge_web_streaming.infrastructure.estatistica.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EstatisticaPublicDataTest {


    @Test
    public void testEstatisticaPublicData() {
        Long quantidadeVisualizacoes = 100L;
        Long quantidadeVideos = 10L;
        Double mediaVisualizacoes = 10.0;

        EstatisticaPublicData estatisticaPublicData = new EstatisticaPublicData(
                quantidadeVisualizacoes,
                quantidadeVideos,
                mediaVisualizacoes
        );

        assertEquals(quantidadeVisualizacoes, estatisticaPublicData.quantidadeVisualizacoes());
        assertEquals(quantidadeVideos, estatisticaPublicData.quantidadeVideos());
        assertEquals(mediaVisualizacoes, estatisticaPublicData.mediaVisualizacoes());
    }
}
