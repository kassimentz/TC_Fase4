package com.fiap.tech_challenge_web_streaming.integration;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsuarioControllerIntegrationTestIT {

    @LocalServerPort
    private int port;

    private String idUsuario1, idUsuario2;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
        criarUsuarios();
    }

    @Test
    public void testAtualizarUsuario() {
        String usuarioUpdateData = "{ \"nome\": \"Novo Nome\", \"email\": \"novoemail@test.com\" }";

        given()
                .contentType(ContentType.JSON)
                .body(usuarioUpdateData)
                .when()
                .put("/usuarios/" + idUsuario1)
                .then()
                .statusCode(200)
                .body("nome", equalTo("Novo Nome"))
                .body("email", equalTo("novoemail@test.com"));
    }

    @Test
    public void testBuscarTodosOsUsuarios() {
        given()
                .when()
                .get("/usuarios")
                .then()
                .statusCode(200);
    }

    @Test
    public void testBuscarUsuarioPorId() {
        given()
                .when()
                .get("/usuarios/" + idUsuario1)
                .then()
                .statusCode(200)
                .body("nome", equalTo("Nome1"))
                .body("email", equalTo("email1@test.com"));
    }

    @Test
    public void testDeletarUsuarioPorId() {
        given()
                .when()
                .delete("/usuarios/" + idUsuario2)
                .then()
                .statusCode(204);
    }

    @Test
    public void criarUsuarios() {
        String requestBody = "{ \"nome\": \"Nome1\", \"email\": \"email1@test.com\" }";

        idUsuario1 = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/usuarios")
                .then()
                .statusCode(201)
                .body("nome", equalTo("Nome1"))
                .body("email", equalTo("email1@test.com"))
                .extract()
                .path("id");

        requestBody = "{ \"nome\": \"Nome2\", \"email\": \"email2@test.com\" }";

        idUsuario2 = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/usuarios")
                .then()
                .statusCode(201)
                .body("nome", equalTo("Nome2"))
                .body("email", equalTo("email2@test.com"))
                .extract()
                .path("id");
    }
}
