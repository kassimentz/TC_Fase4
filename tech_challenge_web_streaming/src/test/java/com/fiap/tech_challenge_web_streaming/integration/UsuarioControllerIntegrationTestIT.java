package com.fiap.tech_challenge_web_streaming.integration;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsuarioControllerIntegrationTestIT {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void testCreateUser() {
        given()
                .contentType(ContentType.JSON)
                .body("{\"name\":\"Test Name\",\"email\":\"test@email.com\"}")
                .when()
                .post("/usuarios")
                .then()
                .statusCode(201)
                .body("name", equalTo("Test Name"))
                .body("email", equalTo("test@email.com"));

//        given()
//                .contentType(ContentType.JSON)
//                .body("{\"name\":\"Test Name\",\"email\":\"test@email.com\"}")
//                .when()
//                .post("/usuarios")
//                .then()
//                .statusCode(201)
//                .body("name", equalTo("Test Name"))
//                .body("email", equalTo("test@email.com"));
    }

    @Test
    public void testGetAllUsers() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/usuarios")
                .then()
                .statusCode(200)
                .body("size()", is(1));
    }

    @Test
    public void testGetUserById() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/usuarios/{id}", "1")
                .then()
                .statusCode(200)
                .body("id", equalTo("1"));
    }

    @Test
    public void testUpdateUser() {
        given()
                .contentType(ContentType.JSON)
                .body("{\"name\":\"Updated Name\",\"email\":\"updated@email.com\"}")
                .when()
                .put("/usuarios/{id}", "1")
                .then()
                .statusCode(200)
                .body("name", equalTo("Updated Name"))
                .body("email", equalTo("updated@email.com"));
    }

    @Test
    public void testDeleteUser() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/usuarios/{id}", "1")
                .then()
                .statusCode(204);
    }

    @Test
    public void testAddFavoriteVideo() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .post("/usuarios/{id}/favoritos/{videoId}", "1", "1")
                .then()
                .statusCode(200)
                .body("videosFavoritados.size()", is(1));
    }

    @Test
    public void testGetRecommendations() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/usuarios/{id}/recomendacoes", "1")
                .then()
                .statusCode(200)
                .body("size()", is(3));
    }
}
