package com.fiap.tech_challenge_web_streaming.infrastructure.usuario.dto;

import com.fiap.tech_challenge_web_streaming.domain.video.entity.Video;
import com.fiap.tech_challenge_web_streaming.usecase.usuario.dto.IUsuarioUpdateData;

import java.util.List;

public record UsuarioUpdateData(

        //Incluir Spring Validation aqui

        String nome,
        String email,
        List<Video> favoritos,
        List<Video> recomendados


) implements IUsuarioUpdateData {
}
