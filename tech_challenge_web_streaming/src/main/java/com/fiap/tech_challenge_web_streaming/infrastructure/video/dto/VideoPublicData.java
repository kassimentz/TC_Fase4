package com.fiap.tech_challenge_web_streaming.infrastructure.video.dto;

import com.fiap.tech_challenge_web_streaming.domain.video.entity.Video;
import com.fiap.tech_challenge_web_streaming.usecase.video.dto.IVideoPublicData;

import java.time.LocalDate;

public record VideoPublicData(
        String id,
        String titulo,
        String descricao,
        String url,
        String categoria,

        LocalDate dataPublicacao


) implements IVideoPublicData {

    public VideoPublicData(Video video) {
        this(
         video.getId(),
         video.getTitulo(),
         video.getDescricao(),
         video.getUrl(),
         video.getCategoria().toString(),
         video.getDataPublicacao()
         );
    }

}
