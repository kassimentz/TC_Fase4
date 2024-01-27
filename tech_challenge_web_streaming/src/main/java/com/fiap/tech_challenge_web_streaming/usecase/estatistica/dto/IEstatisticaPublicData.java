package com.fiap.tech_challenge_web_streaming.usecase.estatistica.dto;

import reactor.core.publisher.Mono;

public interface IEstatisticaPublicData {


    public Long quantidadeVisualizacoes();
    public Long quantidadeVideos();
    public Double mediaVisualizacoes();



}
