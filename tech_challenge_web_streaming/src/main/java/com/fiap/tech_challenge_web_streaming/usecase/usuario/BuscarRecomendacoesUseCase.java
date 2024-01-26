package com.fiap.tech_challenge_web_streaming.usecase.usuario;

import com.fiap.tech_challenge_web_streaming.domain.categoria.entity.Categoria;
import com.fiap.tech_challenge_web_streaming.domain.usuario.gateway.UsuarioGateway;
import com.fiap.tech_challenge_web_streaming.domain.video.entity.Video;
import com.fiap.tech_challenge_web_streaming.domain.video.gateway.VideoGateway;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;
import java.util.stream.Collectors;

public class BuscarRecomendacoesUseCase {
    
    private final UsuarioGateway usuarioGateway;
    private final VideoGateway videoGateway;
    
    public BuscarRecomendacoesUseCase(UsuarioGateway usuarioGateway, VideoGateway videoGateway) {
        this.usuarioGateway = usuarioGateway;
        this.videoGateway = videoGateway;
    }
    
    public Flux<Video> execute(String usuarioId) {
        return buscarRecomendacoes(usuarioId);
    }

    private Flux<Video> buscarRecomendacoes(String usuarioId){
        return usuarioGateway.buscarPorId(usuarioId)
                .flatMapMany(user -> {
                    Set<String> categoriasFavoritas = user.getFavoritos().stream()
                            .map(Video::getCategoria)
                            .map(Categoria::getCategoria)
                            .collect(Collectors.toSet());
                    Flux<Video> videoFlux = videoGateway.buscarTodos()
                            .filterWhen(video -> Mono.just(!user.getFavoritos().contains(video)))
                            .filter(video -> categoriasFavoritas.contains(video.getCategoria().getCategoria()))
                            .sort((v1, v2) -> v2.getUsuariosQueFavoritaram().size() - v1.getUsuariosQueFavoritaram().size());
                    return videoGateway.count()
                            .flatMapMany(count -> {
                                long limit = Math.min(count, 3);
                                return videoFlux.take(limit);
                            });
                });
    }
}
