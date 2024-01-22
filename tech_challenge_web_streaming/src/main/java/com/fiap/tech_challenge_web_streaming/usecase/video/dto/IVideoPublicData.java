package com.fiap.tech_challenge_web_streaming.usecase.video.dto;

import java.time.LocalDate;

public interface IVideoPublicData {

    String id();
    String titulo();
    String descricao();

    String url();
    String categoria();

    LocalDate dataPublicacao();

}
