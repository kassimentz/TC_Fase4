package com.fiap.tech_challenge_web_streaming.integration;

import com.fiap.tech_challenge_web_streaming.domain.categoria.entity.Categoria;
import com.fiap.tech_challenge_web_streaming.domain.video.entity.Video;
import com.fiap.tech_challenge_web_streaming.domain.video.factories.CriteriosBuscaVideo;
import com.fiap.tech_challenge_web_streaming.domain.video.factories.PageData;
import com.fiap.tech_challenge_web_streaming.infrastructure.video.dto.VideoRequestData;
import com.fiap.tech_challenge_web_streaming.infrastructure.video.dto.VideoUpdateData;
import com.fiap.tech_challenge_web_streaming.usecase.usuario.DeletarUsuarioUseCase;
import com.fiap.tech_challenge_web_streaming.usecase.video.*;
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

import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VideoControllerIntegrationTestIT {

    @LocalServerPort
    private int port;

    @Mock
    private AtualizarVideoUseCase atualizarVideoUseCase;

    @Mock
    private BuscarTodosOsVideosUseCase buscarTodosOsVideosUseCase;

    @Mock
    private BuscarTodosOsVideosPorAtributoComPaginacaoUseCase buscarTodosOsVideosPorAtributoComPaginacaoUseCase;

    @Mock
    private BuscarVideoUseCase buscarVideoUseCase;

    @Mock
    private CriarVideoUseCase criarVideoUseCase;

    @Mock
    private DeletarVideoUseCase deletarVideoUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        RestAssured.port = port;
    }

    @Test
    public void testAtualizarVideo() {
        Video video = new Video();
        video.setId("videoId");

        VideoUpdateData videoUpdateData = new VideoUpdateData("title", "description", Categoria.TECNOLOGIA.name(), LocalDate.now());

        when(atualizarVideoUseCase.execute("videoId", videoUpdateData)).thenReturn(Mono.just(video));

        given()
                .contentType(ContentType.JSON)
                .body(videoUpdateData)
                .when()
                .put("/videos/{id}", "videoId")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void testBuscarTodosOsVideos() {
        Video video = new Video();
        video.setId("1");

        when(buscarTodosOsVideosUseCase.execute()).thenReturn(Flux.just(video));

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/videos")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void testBuscarTodosOsVideosPorAtributoComPaginacao() {
        Video video = new Video();
        video.setId("1");

        PageData pageData = PageData.newPageBuilder().page(0).size(10).direcao("ASC").ordenacao("titulo").build();
        CriteriosBuscaVideo criteriosBuscaVideo = CriteriosBuscaVideo.newBuilder().titulo("Test Video").build();

        when(buscarTodosOsVideosPorAtributoComPaginacaoUseCase.execute(pageData, criteriosBuscaVideo)).thenReturn(Flux.just(video));

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/videos?title={title}&page={page}&size={size}", "title", 0, 10)
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void testBuscarVideoPorId() {
        Video video = new Video();
        video.setId("1");

        when(buscarVideoUseCase.execute("1")).thenReturn(Mono.just(video));

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/videos/{id}", "1")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void testCriarVideo() {
        Video video = new Video();
        video.setId("videoId");

        VideoRequestData videoRequestData = new VideoRequestData("title", "description", "TECNOLOGIA", LocalDate.now());

        when(criarVideoUseCase.execute(videoRequestData)).thenReturn(Mono.just(video));

        given()
                .contentType(ContentType.JSON)
                .body(videoRequestData)
                .when()
                .post("/videos")
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void testDeletarVideo() {
        when(deletarVideoUseCase.execute("videoId")).thenReturn(Mono.empty());

        given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/videos/{id}", "videoId")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

}
