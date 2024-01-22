package com.fiap.tech_challenge_web_streaming.usecase.video;

import com.fiap.tech_challenge_web_streaming.domain.video.entity.Video;
import com.fiap.tech_challenge_web_streaming.domain.video.gateway.VideoGateway;
import com.fiap.tech_challenge_web_streaming.usecase.video.dto.IVideoRequestData;
import reactor.core.publisher.Mono;

public class CriarVideoUseCase {

    //talvez para a lógica de salvar o vídeo criar um FileVideoGateway em adição a esse

    private final VideoGateway videoGateway;

    public CriarVideoUseCase(VideoGateway videoGateway) {
        this.videoGateway = videoGateway;
    }

    public Mono<Video> execute(IVideoRequestData dados) {

        //é aqui que entrará a lógica para salvar o arquivo do vídeo

        //TODO trocar null por instancia de Categoria assim que criar a Entity categoria
        Video video = new Video(dados.titulo(), dados.descricao(), dados.dataPublicacao(), null);

        return videoGateway.criar(video);
    }
}
