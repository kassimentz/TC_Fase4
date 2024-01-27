package com.fiap.tech_challenge_web_streaming.infrastructure.video.dto;

import com.fiap.tech_challenge_web_streaming.usecase.video.dto.IVideoUpdateData;

import java.time.LocalDate;

public record VideoUpdateData(

        String titulo,
        String descricao,
        String categoria

) implements IVideoUpdateData {

}
