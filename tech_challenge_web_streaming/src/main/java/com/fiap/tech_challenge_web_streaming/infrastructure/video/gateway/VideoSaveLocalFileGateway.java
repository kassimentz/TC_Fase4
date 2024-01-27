package com.fiap.tech_challenge_web_streaming.infrastructure.video.gateway;

import com.fiap.tech_challenge_web_streaming.domain.video.exception.VideoNaoSalvoException;
import com.fiap.tech_challenge_web_streaming.domain.video.gateway.VideoFileGateway;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class VideoSaveLocalFileGateway implements VideoFileGateway {

    private static final String VIDEO_DIRECTORY = "src/main/resources/videos";

    @Override
    public Mono<String> salvarArquivoVideo(FilePart videoFile) {

        Path destinationDirectory = Paths.get(VIDEO_DIRECTORY);
        Path destinationFile = destinationDirectory.resolve(videoFile.filename());

        try {
            if (!Files.exists(destinationDirectory)) {
                Files.createDirectories(destinationDirectory);
            }
            return videoFile.transferTo(destinationFile).
                    thenReturn(
                            destinationFile.toFile().getAbsolutePath()
                    ).doOnError(throwable -> {
                        throwable.printStackTrace();
                        throw new VideoNaoSalvoException();
                    });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
