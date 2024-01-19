package com.fiap.tech_challenge_web_streaming.usecases;

import com.fiap.tech_challenge_web_streaming.controllers.dto.usuario.UsuarioResponseDTO;
import com.fiap.tech_challenge_web_streaming.entities.Categoria;
import com.fiap.tech_challenge_web_streaming.entities.Usuario;
import com.fiap.tech_challenge_web_streaming.entities.Video;
import com.fiap.tech_challenge_web_streaming.interfaces.UsuarioRepositoryInterface;
import com.fiap.tech_challenge_web_streaming.interfaces.VideoRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UseCases {

    @Autowired
    private UsuarioRepositoryInterface userRepository;

    @Autowired
    private  VideoRepositoryInterface videoRepository;

    public Mono<UsuarioResponseDTO> addVideoFavorito(String usuarioId, String videoId){
        return userRepository.findById(usuarioId)
                .zipWith(videoRepository.findById(videoId))
                .flatMap(tuple -> {
                    Usuario user = tuple.getT1();
                    Video video = tuple.getT2();
                    user.favoritarVideo(video);
                    video.addFavoritadoPorUsuarios(user.getId());
                    return Mono.zip(userRepository.save(user), videoRepository.save(video))
                            .thenReturn(new UsuarioResponseDTO(
                                    user.getId(),
                                    user.getNome(),
                                    user.getEmail(),
                                    user.getVideosFavoritados(),
                                    user.getVideosRecomendados()
                            ));
                });
    }

    public Flux<Video> getRecomendacoes(String idUsuario){
        return userRepository.findById(idUsuario)
                .flatMapMany(user -> {
                    Set<String> categoriasFavoritas = user.getVideosFavoritados().stream()
                            .map(Video::getCategoria)
                            .map(Categoria::getCategoria)
                            .collect(Collectors.toSet());
                    Flux<Video> videoFlux = videoRepository.findAll()
                            .filterWhen(video -> Mono.just(!user.getVideosFavoritados().contains(video)))
                            .filter(video -> categoriasFavoritas.contains(video.getCategoria().getCategoria()))
                            .sort((v1, v2) -> v2.getFavoritadoPorUsuarios().size() - v1.getFavoritadoPorUsuarios().size());
                    return videoRepository.count()
                            .flatMapMany(count -> {
                                long limit = Math.min(count, 3);
                                return videoFlux.take(limit);
                            });
                });
    }
}
