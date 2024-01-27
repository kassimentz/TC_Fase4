package com.fiap.tech_challenge_web_streaming.infrastructure.config;

import com.fiap.tech_challenge_web_streaming.infrastructure.usuario.repository.UsuarioRepository;
import com.fiap.tech_challenge_web_streaming.infrastructure.video.repository.VideoRepository;
import com.fiap.tech_challenge_web_streaming.usecase.usuario.*;
import com.fiap.tech_challenge_web_streaming.usecase.video.*;
import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

 class MvcConfigTest {

    @Test
     void testCriarUsuarioUseCase() {
        UsuarioRepository repository = mock(UsuarioRepository.class);
        MvcConfig mvcConfig = new MvcConfig();
        CriarUsuarioUseCase useCase = mvcConfig.criarUsuarioUseCase(repository);
        assertNotNull(useCase);
    }

    @Test
     void testBuscarUsuarioUseCase() {
        UsuarioRepository repository = mock(UsuarioRepository.class);
        MvcConfig mvcConfig = new MvcConfig();
        BuscarUsuarioUseCase useCase = mvcConfig.buscarUsuarioUseCase(repository);
        assertNotNull(useCase);
    }

    @Test
     void testBuscarTodosUsuariosUseCase() {
        UsuarioRepository repository = mock(UsuarioRepository.class);
        MvcConfig mvcConfig = new MvcConfig();
        BuscarTodosUsuariosUseCase useCase = mvcConfig.buscarTodosUsuariosUseCase(repository);
        assertNotNull(useCase);
    }

    @Test
     void testAtualizarUsuarioUseCase() {
        UsuarioRepository repository = mock(UsuarioRepository.class);
        MvcConfig mvcConfig = new MvcConfig();
        AtualizarUsuarioUseCase useCase = mvcConfig.atualizarUsuarioUseCase(repository);
        assertNotNull(useCase);
    }

    @Test
     void testDeletarUsuarioUseCase() {
        UsuarioRepository repository = mock(UsuarioRepository.class);
        MvcConfig mvcConfig = new MvcConfig();
        DeletarUsuarioUseCase useCase = mvcConfig.deletarUsuarioUseCase(repository);
        assertNotNull(useCase);
    }

    @Test
     void testAddFavoritoUseCase() {
        UsuarioRepository usuarioRepository = mock(UsuarioRepository.class);
        VideoRepository videoRepository = mock(VideoRepository.class);
        ReactiveMongoTemplate reactiveMongoTemplate = mock(ReactiveMongoTemplate.class);
        MvcConfig mvcConfig = new MvcConfig();
        AddFavoritoUseCase useCase = mvcConfig.addFavoritoUseCase(usuarioRepository, videoRepository, reactiveMongoTemplate);
        assertNotNull(useCase);
    }

    @Test
     void testBuscarRecomendacoesUseCase() {
        UsuarioRepository usuarioRepository = mock(UsuarioRepository.class);
        VideoRepository videoRepository = mock(VideoRepository.class);
        ReactiveMongoTemplate reactiveMongoTemplate = mock(ReactiveMongoTemplate.class);
        MvcConfig mvcConfig = new MvcConfig();
        BuscarRecomendacoesUseCase useCase = mvcConfig.buscarRecomendacoesUseCase(usuarioRepository, videoRepository, reactiveMongoTemplate);
        assertNotNull(useCase);
    }

    @Test
     void testBuscarVideoUseCase() {
        VideoRepository repository = mock(VideoRepository.class);
        ReactiveMongoTemplate template = mock(ReactiveMongoTemplate.class);
        MvcConfig mvcConfig = new MvcConfig();
        BuscarVideoUseCase useCase = mvcConfig.buscarVideoUseCase(repository, template);
        assertNotNull(useCase);
    }

    @Test
     void testBuscarTodosOsVideosUseCase() {
        VideoRepository repository = mock(VideoRepository.class);
        ReactiveMongoTemplate template = mock(ReactiveMongoTemplate.class);
        MvcConfig mvcConfig = new MvcConfig();
        BuscarTodosOsVideosUseCase useCase = mvcConfig.buscarTodosOsVideosUseCase(repository, template);
        assertNotNull(useCase);
    }

    @Test
     void testBuscarTodosOsVideosPorAtributoComPaginacaoUseCase() {
        VideoRepository repository = mock(VideoRepository.class);
        ReactiveMongoTemplate template = mock(ReactiveMongoTemplate.class);
        MvcConfig mvcConfig = new MvcConfig();
        BuscarTodosOsVideosPorAtributoComPaginacaoUseCase useCase = mvcConfig.buscarTodosOsVideosPorAtributoComPaginacaoUseCase(repository, template);
        assertNotNull(useCase);
    }

    @Test
     void testCriarVideoUseCase() {
        VideoRepository repository = mock(VideoRepository.class);
        ReactiveMongoTemplate template = mock(ReactiveMongoTemplate.class);
        MvcConfig mvcConfig = new MvcConfig();
        CriarVideoUseCase useCase = mvcConfig.criarVideoUseCase(repository, template);
        assertNotNull(useCase);
    }

    @Test
     void testDeletarVideoUseCase() {
        VideoRepository repository = mock(VideoRepository.class);
        ReactiveMongoTemplate template = mock(ReactiveMongoTemplate.class);
        MvcConfig mvcConfig = new MvcConfig();
        DeletarVideoUseCase useCase = mvcConfig.deletarVideoUseCase(repository, template);
        assertNotNull(useCase);
    }

    @Test
     void testAtualizarVideoUseCase() {
        VideoRepository repository = mock(VideoRepository.class);
        ReactiveMongoTemplate template = mock(ReactiveMongoTemplate.class);
        MvcConfig mvcConfig = new MvcConfig();
        AtualizarVideoUseCase useCase = mvcConfig.atualizarVideoUseCase(repository, template);
        assertNotNull(useCase);
    }
}
