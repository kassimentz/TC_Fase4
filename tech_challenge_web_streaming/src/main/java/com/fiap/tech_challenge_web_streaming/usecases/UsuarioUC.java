package com.fiap.tech_challenge_web_streaming.usecases;

import com.fiap.tech_challenge_web_streaming.controllers.dto.usuario.UsuarioResponseDTO;
import com.fiap.tech_challenge_web_streaming.entities.Usuario;
import com.fiap.tech_challenge_web_streaming.interfaces.UsuarioRepositoryInterface;
import com.fiap.tech_challenge_web_streaming.interfaces.VideoRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class UsuarioUC {

    @Autowired
    private UsuarioRepositoryInterface repository;

    @Autowired
    VideoRepositoryInterface videoRepository;

    public Mono<UsuarioResponseDTO> addFavorito(String id, String idFilme){
        Mono<Usuario> usuario = repository.findById(id);
        return usuario.map(u -> {
            u.addFavorito(videoRepository.findById(idFilme).block());
            return u;
        }).flatMap(repository::save)
                .map(u -> new UsuarioResponseDTO(
                        u.getId(),
                        u.getNome(),
                        u.getEmail(),
                        u.getFavoritos(),
                        u.getRecomendados()
                ));
    }

}
