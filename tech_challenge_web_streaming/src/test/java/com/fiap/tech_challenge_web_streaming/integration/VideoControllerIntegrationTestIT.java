package com.fiap.tech_challenge_web_streaming.integration;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VideoControllerIntegrationTestIT {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
        RestAssured.defaultParser = Parser.JSON;
    }

    @Test
    public void testCreateVideo() {
        given()
                .contentType(ContentType.JSON)
                .body("{\"titulo\":\"Test Title\",\"descricao\":\"Test Description\",\"url\":\"http://test.com\"}")
                .when()
                .post("/videos")
                .then()
                .statusCode(201)
                .body("titulo", equalTo("Test Title"))
                .body("descricao", equalTo("Test Description"))
                .body("url", equalTo("http://test.com"));
    }

    @Test
    public void testGetAllVideos() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/videos")
                .then()
                .statusCode(200)
                .body("size()", is(1));
    }

    @Test
    public void testGetVideoById() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/videos/{id}", "1")
                .then()
                .statusCode(200)
                .body("id", equalTo("1"));
    }

    @Test
    public void testUpdateVideo() {
        given()
                .contentType(ContentType.JSON)
                .body("{\"titulo\":\"Test Title\",\"descricao\":\"Test Description\",\"url\":\"http://test.com\"}")
                .when()
                .put("/videos/{id}", "65aadb77578410188fb7dcfd")
                .then()
                .statusCode(200)
                .body("titulo", equalTo("Test Title"))
                .body("descricao", equalTo("Test Description"))
                .body("url", equalTo("http://test.com"));
    }

    @Test
    public void testDeleteVideo() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/videos/{id}", "1")
                .then()
                .statusCode(204);
    }
}
