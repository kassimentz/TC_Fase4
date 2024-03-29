package com.fiap.tech_challenge_web_streaming.usecase.video.dto;

import java.time.LocalDate;

public interface IVideoRequestData {

    // url e id não enviados pois serão gerados
    String titulo();
    String descricao();

    String categoria();

}
