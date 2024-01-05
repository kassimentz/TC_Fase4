package com.fiap.tech_challenge_web_streaming.interfaces;

import com.fiap.tech_challenge_web_streaming.controllers.dto.UsuarioRequestDTO;
import com.fiap.tech_challenge_web_streaming.controllers.dto.UsuarioResponseDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;


public interface UsuarioGatewayInterface {

    Mono<UsuarioResponseDTO> novo(UsuarioRequestDTO usuario);
    Flux<UsuarioResponseDTO> listar();

    Mono<Optional<UsuarioResponseDTO>> listarPorId(Long id);

    Mono<UsuarioResponseDTO> atualiza(Long id, UsuarioRequestDTO usuario);

    void deletar(Long id);
}
