package com.fiap.tech_challenge_web_streaming.infrastructure.usuario.dto;

import com.fiap.tech_challenge_web_streaming.domain.usuario.entity.Usuario;
import com.fiap.tech_challenge_web_streaming.domain.video.entity.Video;
import com.fiap.tech_challenge_web_streaming.usecase.usuario.dto.IUsuarioPublicData;

import java.util.List;

public record UsuarioPublicData(

        String id,
        String nome,
        String email,
        List<Video> favoritos,
        List<Video> recomendados

) implements IUsuarioPublicData {

    public UsuarioPublicData(Usuario usuario) {
        this (
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getFavoritos(),
                usuario.getRecomendados()

        );
    }


}
