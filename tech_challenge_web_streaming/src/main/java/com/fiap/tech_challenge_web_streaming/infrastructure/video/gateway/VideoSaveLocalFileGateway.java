package com.fiap.tech_challenge_web_streaming.infrastructure.video.gateway;

import com.fiap.tech_challenge_web_streaming.domain.video.exception.VideoNaoSalvoException;
import com.fiap.tech_challenge_web_streaming.domain.video.gateway.VideoFileGateway;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;

import java.nio.file.Path;
import java.nio.file.Paths;

public class VideoSaveLocalFileGateway implements VideoFileGateway {
    @Override
    public Mono<String> salvarArquivoVideo(FilePart videoFile) {

        Path destinationFile = Paths.get("src/main/resources/videos", videoFile.filename());

        return videoFile.transferTo(destinationFile).
                thenReturn(
                destinationFile.toFile().getAbsolutePath()
        ).doOnError(throwable -> {
            throwable.printStackTrace();
            throw new VideoNaoSalvoException();});

    }



}
