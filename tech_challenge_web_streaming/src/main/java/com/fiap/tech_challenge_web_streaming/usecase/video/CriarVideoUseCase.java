package com.fiap.tech_challenge_web_streaming.usecase.video;

import com.fiap.tech_challenge_web_streaming.domain.categoria.entity.Categoria;
import com.fiap.tech_challenge_web_streaming.domain.video.entity.Video;
import com.fiap.tech_challenge_web_streaming.domain.video.gateway.VideoFileGateway;
import com.fiap.tech_challenge_web_streaming.domain.video.gateway.VideoGateway;
import com.fiap.tech_challenge_web_streaming.usecase.video.dto.IVideoRequestData;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;

public class CriarVideoUseCase {


    private final VideoGateway videoGateway;
    private final VideoFileGateway videoFileGateway;

    public CriarVideoUseCase(VideoGateway videoGateway, VideoFileGateway videoFileGateway) {
        this.videoGateway = videoGateway;
        this.videoFileGateway = videoFileGateway;
    }

    public Mono<Video> execute(IVideoRequestData videoMetadata, FilePart videoFile) {

        return videoFileGateway.salvarArquivoVideo(videoFile)
                .flatMap(videoUrl -> {
                    Video video = new Video(videoMetadata.titulo(), videoMetadata.descricao(),
                            Categoria.valueOf(videoMetadata.categoria()),
                            videoUrl);
                    return videoGateway.criar(video);
                });
    }
}
