package com.fiap.tech_challenge_web_streaming.integration;

import com.fiap.tech_challenge_web_streaming.domain.usuario.entity.Usuario;
import com.fiap.tech_challenge_web_streaming.domain.usuario.gateway.UsuarioGateway;
import com.fiap.tech_challenge_web_streaming.domain.video.entity.Video;
import com.fiap.tech_challenge_web_streaming.infrastructure.usuario.dto.UsuarioAddFavoritoRequestData;

import com.fiap.tech_challenge_web_streaming.usecase.usuario.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
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
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

    String idUsuario1, idUsuario2;

    @BeforeEach
     void setUp() {
        MockitoAnnotations.openMocks(this);
        RestAssured.port = port;
    }

    @Test
     void testCriarUsuario() {

        String requestBody = "{"
                + "\"nome\":\"test\","
                + "\"email\":\"test@test.com\""
                + "}";

        String id = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/usuarios")
                .then()
                .statusCode(201)
                .body("nome", equalTo("test"))
                .extract()
                .path("id");

        idUsuario1 =  id;

        requestBody = "{"
                + "\"nome\":\"test2\","
                + "\"email\":\"test2@test.com\""
                + "}";

        String id2 = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/usuarios")
                .then()
                .statusCode(201)
                .body("nome", equalTo("test2"))
                .extract()
                .path("id");

            idUsuario2 = id2;
    }

    @Test
    public void testAtualizarUsuario() {

        testCriarUsuario();

        String requestBody = "{"
                + "\"nome\":\"updatedName\","
                + "\"email\":\"updatedEmail@test.com\""
                + "}";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put("/usuarios/{id}", idUsuario1)
                .then()
                .statusCode(200)
                .body("nome", equalTo("updatedName"));
    }

    @Test
    public void testDeletarUsuarioPorId() {
        testCriarUsuario();
        given()
                .when()
                .delete("/usuarios/{id}", idUsuario2)
                .then()
                .statusCode(204);
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
    public void testBuscarUsuarioporId() {

        testCriarUsuario();

        Response response = RestAssured
                .given()
                .baseUri("http://localhost")
                .port(port)
                .when()
                .get("/usuarios/" + idUsuario1);

        assertEquals(200, response.getStatusCode());
        assertEquals(idUsuario1, response.jsonPath().getString("id"));

    }
}
