package com.fiap.tech_challenge_web_streaming.integration;

import com.fiap.tech_challenge_web_streaming.domain.usuario.entity.Usuario;
import com.fiap.tech_challenge_web_streaming.domain.usuario.gateway.UsuarioGateway;
import com.fiap.tech_challenge_web_streaming.domain.video.entity.Video;
import com.fiap.tech_challenge_web_streaming.infrastructure.usuario.dto.UsuarioAddFavoritoRequestData;
import com.fiap.tech_challenge_web_streaming.infrastructure.usuario.dto.UsuarioRequestData;
import com.fiap.tech_challenge_web_streaming.usecase.usuario.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static io.restassured.RestAssured.given;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
 class UsuarioControllerIntegrationTestIT {

    @LocalServerPort
    private int port;

    @Mock
    private UsuarioGateway usuarioGateway;

    @Mock
    private AddFavoritoUseCase adicionarVideoFavoritoUseCase;
    @Mock
    private AtualizarUsuarioUseCase atualizarUsuarioUseCase;

    @Mock
    private BuscarRecomendacoesUseCase buscarRecomendacoesUseCase;

    @Mock
    private BuscarTodosUsuariosUseCase buscarTodosUsuariosUseCase;

    @Mock
    private BuscarUsuarioUseCase buscarUsuarioUseCase;

    @Mock
    private CriarUsuarioUseCase criarUsuarioUseCase;

    @Mock
    private DeletarUsuarioUseCase deletarUsuarioUseCase;

    @BeforeEach
     void setUp() {
        MockitoAnnotations.openMocks(this);
        RestAssured.port = port;
    }

    @Test
     void testGetUsuario() {
        Usuario usuario = new Usuario();
        usuario.setId("1");

        when(usuarioGateway.buscarPorId("1")).thenReturn(Mono.just(usuario));

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/usuarios/{id}", "1")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
     void testCriarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setId("1");

        UsuarioRequestData usuarioRequestData = new UsuarioRequestData("nome", "email");

        when(criarUsuarioUseCase.executar(usuarioRequestData)).thenReturn(Mono.just(usuario));

        given()
                .contentType(ContentType.JSON)
                .body(usuarioRequestData)
                .when()
                .post("/usuarios")
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
     void testAtualizarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setId("1");

        UsuarioRequestData usuarioUpdateData = new UsuarioRequestData("nome", "email");

        when(atualizarUsuarioUseCase.execute("1", usuarioUpdateData)).thenReturn(Mono.just(usuario));

        given()
                .contentType(ContentType.JSON)
                .body(usuarioUpdateData)
                .when()
                .put("/usuarios/{id}", "1")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
     void testDeletarUsuarioPorId() {
        when(deletarUsuarioUseCase.execute("1")).thenReturn(Mono.empty());

        given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/usuarios/{id}", "1")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
     void testGetUsuarioNotFound() {
        when(usuarioGateway.buscarPorId("1")).thenReturn(Mono.empty());

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/usuarios/{id}", "1")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
     void testAdicionarVideoFavorito() {
        Usuario usuario = new Usuario();
        usuario.setId("1");

        UsuarioAddFavoritoRequestData usuarioAddFavoritoRequestData = new UsuarioAddFavoritoRequestData("1");

        when(adicionarVideoFavoritoUseCase.execute("1", usuarioAddFavoritoRequestData)).thenReturn(Mono.just(usuario));

        given()
                .contentType(ContentType.JSON)
                .body(usuarioAddFavoritoRequestData)
                .when()
                .post("/usuarios/{id}/favoritos/", "1")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
     void testBuscarRecomendacoes() {
        Video video = new Video();
        video.setId("videoId");

        when(buscarRecomendacoesUseCase.execute("1")).thenReturn(Flux.just(video));

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/usuarios/{id}/recomendacoes", "1")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
     void testBuscarTodosUsuarios() {
        Usuario usuario = new Usuario();
        usuario.setId("1");

        when(buscarTodosUsuariosUseCase.execute()).thenReturn(Flux.just(usuario));

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/usuarios")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
     void testBuscarUsuarioporId() {
        Usuario usuario = new Usuario();
        usuario.setId("1");

        when(buscarUsuarioUseCase.execute("1")).thenReturn(Mono.just(usuario));

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/usuarios/{id}", "1")
                .then()
                .statusCode(HttpStatus.OK.value());
    }
}
