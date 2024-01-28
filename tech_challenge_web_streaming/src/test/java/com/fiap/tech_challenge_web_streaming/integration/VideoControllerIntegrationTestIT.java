package com.fiap.tech_challenge_web_streaming.integration;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VideoControllerIntegrationTestIT {

    @LocalServerPort
    private int port;

    private String idVideo1, idVideo2;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
        createVideos();
    }

    @Test
    public void testAtualizarVideo() {
        String videoUpdateData = "{ \"titulo\": \"Novo Titulo\", \"descricao\": \"Nova Descricao\", \"categoria\": \"PETS\" }";

        given()
                .contentType(ContentType.JSON)
                .body(videoUpdateData)
                .when()
                .put("/videos/" + idVideo1)
                .then()
                .statusCode(200)
                .body("titulo", equalTo("Novo Titulo"))
                .body("descricao", equalTo("Nova Descricao"))
                .body("categoria", equalTo("PETS"));
    }

    @Test
    public void testBuscarTodosOsVideos() {
        given()
                .when()
                .get("/videos")
                .then()
                .statusCode(200);
    }

    @Test
    public void testBuscarTodosOsVideosPorAtributoComPaginacao() {
        given()
                .param("page", 0)
                .param("size", 10)
                .param("direcao", "DESC")
                .param("ordenacao", "id")
                .param("titulo", "Titulo")
                .param("dataPublicacao", "2022-01-01")
                .param("categoria", "Categoria")
                .when()
                .get("/videos/filtrar")
                .then()
                .statusCode(200);
    }

    @Test
    public void testBuscarVideoPorId() {
        given()
                .when()
                .get("/videos/" + idVideo1)
                .then()
                .statusCode(200)
                .body("titulo", equalTo("Titulo1"))
                .body("descricao", equalTo("Descricao1"))
                .body("categoria", equalTo("PETS"));
    }

    @Test
    public void testDeletarVideo() {
        given()
                .when()
                .delete("/videos/" + idVideo2)
                .then()
                .statusCode(204);
    }

    @Test
    public void createVideos() {
        try {
            String videoMetadata = "{ \"titulo\": \"Titulo1\", \"descricao\": \"Descricao1\", \"categoria\": \"PETS\" }";
            File videoFile = new ClassPathResource("videos/video.mp4").getFile();

            idVideo1 = given()
                    .multiPart("videoMetadata", videoMetadata, "application/json")
                    .multiPart("videoFile", videoFile)
                    .contentType(ContentType.MULTIPART)
                    .when()
                    .post("/videos")
                    .then()
                    .statusCode(201)
                    .body("titulo", equalTo("Titulo1"))
                    .body("descricao", equalTo("Descricao1"))
                    .body("categoria", equalTo("PETS"))
                    .extract()
                    .path("id");

            videoMetadata = "{ \"titulo\": \"Titulo2\", \"descricao\": \"Descricao2\", \"categoria\": \"PETS\" }";

            idVideo2 = given()
                    .multiPart("videoMetadata", videoMetadata, "application/json")
                    .multiPart("videoFile", videoFile)
                    .contentType(ContentType.MULTIPART)
                    .when()
                    .post("/videos")
                    .then()
                    .statusCode(201)
                    .body("titulo", equalTo("Titulo2"))
                    .body("descricao", equalTo("Descricao2"))
                    .body("categoria", equalTo("PETS"))
                    .extract()
                    .path("id");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
