package com.fiap.tech_challenge_web_streaming.infrastructure.usuario.dto;

import com.fiap.tech_challenge_web_streaming.domain.video.entity.Video;
import com.fiap.tech_challenge_web_streaming.usecase.usuario.dto.IUsuarioRequestData;

import java.util.List;

public record UsuarioRequestData(

        //Incluir Spring Validation aqui

        String nome,
        String email,
        List<Video> favoritos,
        List<Video> recomendados


) implements IUsuarioRequestData {

}
