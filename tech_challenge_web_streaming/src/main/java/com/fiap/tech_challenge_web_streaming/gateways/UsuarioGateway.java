package com.fiap.tech_challenge_web_streaming.gateways;

import com.fiap.tech_challenge_web_streaming.controllers.dto.usuario.UsuarioNovoResponseDTO;
import com.fiap.tech_challenge_web_streaming.controllers.dto.usuario.UsuarioRequestDTO;
import com.fiap.tech_challenge_web_streaming.controllers.dto.usuario.UsuarioResponseDTO;
import com.fiap.tech_challenge_web_streaming.entities.Usuario;
import com.fiap.tech_challenge_web_streaming.entities.Video;
import com.fiap.tech_challenge_web_streaming.interfaces.UsuarioGatewayInterface;
import com.fiap.tech_challenge_web_streaming.interfaces.UsuarioRepositoryInterface;
import com.fiap.tech_challenge_web_streaming.interfaces.VideoRepositoryInterface;
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

    @Autowired
    private VideoRepositoryInterface videoRepository;

    @Override
    public Mono<UsuarioNovoResponseDTO> novo(UsuarioRequestDTO request){
        Usuario usuario = new Usuario(request);
        Mono<Usuario> saved = repository.save(usuario);
        return saved.map(u -> new UsuarioNovoResponseDTO(
                u.getId(),
                u.getNome(),
                u.getEmail()
        ));
    }

    @Override
    public Flux<UsuarioResponseDTO> listar(){
        return repository.findAll()
                .map(usuario -> new UsuarioResponseDTO(
                        usuario.getId(),
                        usuario.getNome(),
                        usuario.getEmail()
                ));
    }

    @Override
    public Mono<Optional<UsuarioResponseDTO>> listarPorId(String id){
        Mono<Usuario> usuario = repository.findById(id);
        return usuario.map(u -> Optional.of(new UsuarioResponseDTO(
                u.getId(),
                u.getNome(),
                u.getEmail(),
                encontraVideosDoUsuario(u.getVideosFavoritados()),
                encontraVideosDoUsuario(u.getVideosRecomendados())
        )));
    }

    private List<Video> encontraVideosDoUsuario(List<String> videosFavoritados){
        return videoRepository.findAllById(videosFavoritados).collectList().block();
    }

    @Override
    public Mono<UsuarioResponseDTO> atualiza(String id, UsuarioRequestDTO request){
        Mono<Usuario> usuarioExistente = repository.findById(id);
        return usuarioExistente.map(usuario -> {
            usuario.setNome(request.getNome());
            usuario.setEmail(request.getEmail());
            return usuario;
        }).flatMap(repository::save).map(u -> new UsuarioResponseDTO(
                u.getId(),
                u.getNome(),
                u.getEmail(),
                encontraVideosDoUsuario(u.getVideosFavoritados()),
                encontraVideosDoUsuario(u.getVideosRecomendados())
        ));
    }

    @Override
    public Mono<Void> deletar(String id){
        Mono<Usuario> usuario = repository.findById(id);
        return usuario.flatMap(repository::delete);
    }

    @Override
    public Flux<Video> getRecomendacoes(String id){
        return null;
    }

    @Override
    public Mono<UsuarioResponseDTO> addVideoFavorito(String usuarioId, String videoId){
        return repository.findById(usuarioId)
                .zipWith(videoRepository.findById(videoId))
                .flatMap(tuple -> {
                    Usuario user = tuple.getT1();
                    Video video = tuple.getT2();
                    user.favoritarVideo(video.getId());
                    video.addFavoritadoPorUsuarios(user.getId());
                    return Mono.zip(repository.save(user), videoRepository.save(video))
                            .thenReturn(new UsuarioResponseDTO(
                                    user.getId(),
                                    user.getNome(),
                                    user.getEmail(),
                                    encontraVideosDoUsuario(user.getVideosFavoritados()),
                                    encontraVideosDoUsuario(user.getVideosRecomendados())
                            ));
                });
    }

}
