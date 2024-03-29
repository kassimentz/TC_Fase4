package com.fiap.tech_challenge_web_streaming.usecase.usuario.dto;

import com.fiap.tech_challenge_web_streaming.domain.video.entity.Video;

import java.util.List;

public interface IUsuarioPublicData {

    String id();
    String nome ();

    String email ();

    List<com.fiap.tech_challenge_web_streaming.domain.video.entity.Video> favoritos ();

    List<Video> recomendados ();

}
