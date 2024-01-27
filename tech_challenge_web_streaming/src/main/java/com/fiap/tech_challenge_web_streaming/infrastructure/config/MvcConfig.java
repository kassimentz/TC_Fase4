package com.fiap.tech_challenge_web_streaming.infrastructure.config;

import com.fiap.tech_challenge_web_streaming.domain.usuario.gateway.UsuarioGateway;
import com.fiap.tech_challenge_web_streaming.domain.video.gateway.VideoFileGateway;
import com.fiap.tech_challenge_web_streaming.domain.video.gateway.VideoGateway;
import com.fiap.tech_challenge_web_streaming.domain.video.gateway.VideoStreamGateway;
import com.fiap.tech_challenge_web_streaming.infrastructure.usuario.gateway.UsuarioDatabaseGateway;
import com.fiap.tech_challenge_web_streaming.infrastructure.usuario.repository.UsuarioRepository;
import com.fiap.tech_challenge_web_streaming.infrastructure.video.gateway.VideoDatabaseGateway;
import com.fiap.tech_challenge_web_streaming.infrastructure.video.gateway.VideoSaveLocalFileGateway;
import com.fiap.tech_challenge_web_streaming.infrastructure.video.gateway.VideoStreamGatewayImpl;
import com.fiap.tech_challenge_web_streaming.infrastructure.video.repository.VideoRepository;
import com.fiap.tech_challenge_web_streaming.usecase.usuario.*;
import com.fiap.tech_challenge_web_streaming.usecase.video.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

@Configuration
public class MvcConfig {

    @Bean
    public CriarUsuarioUseCase criarUsuarioUseCase(UsuarioRepository repository){
        UsuarioGateway usuarioGateway = new UsuarioDatabaseGateway(repository);
        return new CriarUsuarioUseCase(usuarioGateway);

    }

    @Bean
    public BuscarUsuarioUseCase buscarUsuarioUseCase(UsuarioRepository repository){
        UsuarioGateway usuarioGateway = new UsuarioDatabaseGateway(repository);
        return new BuscarUsuarioUseCase(usuarioGateway);

    }

    @Bean
    public BuscarTodosUsuariosUseCase buscarTodosUsuariosUseCase(UsuarioRepository repository){
        UsuarioGateway usuarioGateway = new UsuarioDatabaseGateway(repository);
        return new BuscarTodosUsuariosUseCase(usuarioGateway);
    }

    @Bean
    public AtualizarUsuarioUseCase atualizarUsuarioUseCase(UsuarioRepository repository){
        UsuarioGateway usuarioGateway = new UsuarioDatabaseGateway(repository);
        return new AtualizarUsuarioUseCase(usuarioGateway);
    }

    @Bean
    public DeletarUsuarioUseCase deletarUsuarioUseCase(UsuarioRepository repository){
        UsuarioGateway usuarioGateway = new UsuarioDatabaseGateway(repository);
        return new DeletarUsuarioUseCase(usuarioGateway);
    }

    @Bean
    public AddFavoritoUseCase addFavoritoUseCase(UsuarioRepository usuarioRepository, VideoRepository videoRepository, ReactiveMongoTemplate reactiveMongoTemplate){
        UsuarioGateway usuarioGateway = new UsuarioDatabaseGateway(usuarioRepository);
        VideoGateway videoGateway = new VideoDatabaseGateway(videoRepository, reactiveMongoTemplate);
        return new AddFavoritoUseCase(usuarioGateway, videoGateway);
    }

    @Bean
    public BuscarRecomendacoesUseCase buscarRecomendacoesUseCase(UsuarioRepository usuarioRepository, VideoRepository videoRepository, ReactiveMongoTemplate reactiveMongoTemplate){
        UsuarioGateway usuarioGateway = new UsuarioDatabaseGateway(usuarioRepository);
        VideoGateway videoGateway = new VideoDatabaseGateway(videoRepository, reactiveMongoTemplate);
        return new BuscarRecomendacoesUseCase(usuarioGateway, videoGateway);
    }

    @Bean
    public BuscarVideoUseCase buscarVideoUseCase(VideoRepository repository, ReactiveMongoTemplate template) {
        VideoGateway videoGateway = new VideoDatabaseGateway(repository, template);
        return new BuscarVideoUseCase(videoGateway);
    }

    @Bean
    public BuscarTodosOsVideosUseCase buscarTodosOsVideosUseCase(VideoRepository repository, ReactiveMongoTemplate template) {
        VideoGateway videoGateway = new VideoDatabaseGateway(repository, template);
        return new BuscarTodosOsVideosUseCase(videoGateway);
    }

    @Bean
    public BuscarTodosOsVideosPorAtributoComPaginacaoUseCase buscarTodosOsVideosPorAtributoComPaginacaoUseCase(VideoRepository repository, ReactiveMongoTemplate template) {
        VideoGateway videoGateway = new VideoDatabaseGateway(repository, template);
        return new BuscarTodosOsVideosPorAtributoComPaginacaoUseCase(videoGateway);
    }


    @Bean
    public CriarVideoUseCase criarVideoUseCase(VideoRepository repository, ReactiveMongoTemplate template) {
        VideoGateway videoGateway = new VideoDatabaseGateway(repository, template);
        VideoFileGateway videoFileGateway = new VideoSaveLocalFileGateway();
        return new CriarVideoUseCase(videoGateway, videoFileGateway);
    }

    @Bean
    public DeletarVideoUseCase deletarVideoUseCase(VideoRepository repository, ReactiveMongoTemplate template) {
        VideoGateway videoGateway = new VideoDatabaseGateway(repository, template);
        return new DeletarVideoUseCase(videoGateway);
    }


    @Bean
    public AtualizarVideoUseCase atualizarVideoUseCase(VideoRepository repository, ReactiveMongoTemplate template) {
        VideoGateway videoGateway = new VideoDatabaseGateway(repository, template);
        return new AtualizarVideoUseCase(videoGateway);
    }


    @Bean
    public VideoStreamUseCase videoStreamUseCase(VideoRepository repository)  {
        VideoStreamGateway videoGateway = new VideoStreamGatewayImpl(repository);
        return new VideoStreamUseCase(videoGateway);
    }
}
