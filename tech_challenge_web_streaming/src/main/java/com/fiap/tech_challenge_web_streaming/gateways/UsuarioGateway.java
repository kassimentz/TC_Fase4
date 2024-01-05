package com.fiap.tech_challenge_web_streaming.gateways;

import com.fiap.tech_challenge_web_streaming.controllers.dto.UsuarioRequestDTO;
import com.fiap.tech_challenge_web_streaming.controllers.dto.UsuarioResponseDTO;
import com.fiap.tech_challenge_web_streaming.entities.Usuario;
import com.fiap.tech_challenge_web_streaming.interfaces.UsuarioGatewayInterface;
import com.fiap.tech_challenge_web_streaming.interfaces.UsuarioRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioGateway implements UsuarioGatewayInterface {

    @Autowired
    private UsuarioRepositoryInterface repository;

    @Override
    public Mono<UsuarioResponseDTO> novo(UsuarioRequestDTO request){
        Usuario usuario = new Usuario(request);
        Mono<Usuario> saved = repository.save(usuario);
        return saved.map(u -> new UsuarioResponseDTO(
                u.getId(),
                u.getNome(),
                u.getEmail(),
                u.getFavoritos(),
                u.getRecomendados()
        ));
    }

    @Override
    public Flux<UsuarioResponseDTO> listar(){
        return repository.findAll()
                .map(usuario -> new UsuarioResponseDTO(
                        usuario.getId(),
                        usuario.getNome(),
                        usuario.getEmail(),
                        usuario.getFavoritos(),
                        usuario.getRecomendados()
                ));
    }

    @Override
    public Mono<Optional<UsuarioResponseDTO>> listarPorId(Long id){
        Mono<Usuario> usuario = repository.findById(id);
        return usuario.map(u -> Optional.of(new UsuarioResponseDTO(
                u.getId(),
                u.getNome(),
                u.getEmail(),
                u.getFavoritos(),
                u.getRecomendados()
        )));
    }

    @Override
    public Mono<UsuarioResponseDTO> atualiza(Long id, UsuarioRequestDTO request){
        Mono<Usuario> usuarioExistente = repository.findById(id);
        return usuarioExistente.map(usuario -> {
            usuario.setNome(request.getNome());
            usuario.setEmail(request.getEmail());
            return usuario;
        }).flatMap(repository::save).map(u -> new UsuarioResponseDTO(
                u.getId(),
                u.getNome(),
                u.getEmail(),
                u.getFavoritos(),
                u.getRecomendados()
        ));
    }

    @Override
    public void deletar(Long id){
        Mono<Usuario> usuarioExistente = repository.findById(id);
        usuarioExistente.map(usuario -> {
            repository.delete(usuario);
            return usuario; //return from map
        });
    }
}
