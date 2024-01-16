package com.fiap.tech_challenge_web_streaming.interfaces;

import com.fiap.tech_challenge_web_streaming.controllers.dto.usuario.UsuarioNovoResponseDTO;
import com.fiap.tech_challenge_web_streaming.controllers.dto.usuario.UsuarioRequestDTO;
import com.fiap.tech_challenge_web_streaming.controllers.dto.usuario.UsuarioResponseDTO;
import com.fiap.tech_challenge_web_streaming.entities.Video;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;


public interface UsuarioGatewayInterface {

    Mono<UsuarioNovoResponseDTO> novo(UsuarioRequestDTO usuario);
    Flux<UsuarioResponseDTO> listar();

    Mono<Optional<UsuarioResponseDTO>> listarPorId(String id);

    Mono<UsuarioResponseDTO> atualiza(String id, UsuarioRequestDTO usuario);

    Mono<Void> deletar(String id);

    Flux<Video> getRecomendacoes(String id);

    Mono<UsuarioResponseDTO> addVideoFavorito(String usuarioId, String videoId);

}
