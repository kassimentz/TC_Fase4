package com.fiap.tech_challenge_web_streaming.controller;

import com.fiap.tech_challenge_web_streaming.controllers.UsuarioController;
import com.fiap.tech_challenge_web_streaming.controllers.dto.usuario.UsuarioNovoResponseDTO;
import com.fiap.tech_challenge_web_streaming.controllers.dto.usuario.UsuarioRequestDTO;
import com.fiap.tech_challenge_web_streaming.controllers.dto.usuario.UsuarioResponseDTO;
import com.fiap.tech_challenge_web_streaming.interfaces.UsuarioGatewayInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UsuarioControllerTest {

    @InjectMocks
    private UsuarioController usuarioController;

    @Mock
    private UsuarioGatewayInterface usuarioGateway;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateUser() {
        UsuarioRequestDTO requestDTO = new UsuarioRequestDTO("Test Name", "test@email.com");
        UsuarioNovoResponseDTO responseDTO = new UsuarioNovoResponseDTO("1234", "Test Name", "test@email.com");
        when(usuarioGateway.novo(requestDTO)).thenReturn(Mono.just(responseDTO));

        ResponseEntity<UsuarioNovoResponseDTO> result = usuarioController.createUser(requestDTO).block();

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(responseDTO, result.getBody());
    }

    @Test
    public void testGetAll() {
        UsuarioResponseDTO responseDTO = new UsuarioResponseDTO("1234", "Test Name", "test@email.com", new ArrayList<>(), new ArrayList<>());
        when(usuarioGateway.listar()).thenReturn(Flux.just(responseDTO));

        ResponseEntity<Flux<UsuarioResponseDTO>> result = usuarioController.getAll();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(responseDTO, result.getBody().blockFirst());
    }

    @Test
    public void testUserById() {
        String testId = "1234";
        UsuarioResponseDTO responseDTO = new UsuarioResponseDTO(testId, "Test Name", "test@email.com", new ArrayList<>(), new ArrayList<>());
        when(usuarioGateway.listarPorId(testId)).thenReturn(Mono.just(Optional.of(responseDTO)));

        ResponseEntity<Optional<UsuarioResponseDTO>> result = usuarioController.userById(testId).block();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(responseDTO, result.getBody().get());
    }

    @Test
    public void testAtualizarUsuario() {
        String testId = "1234";
        UsuarioRequestDTO requestDTO = new UsuarioRequestDTO("New Test Name", "newtest@email.com");
        UsuarioResponseDTO responseDTO = new UsuarioResponseDTO(testId, "New Test Name", "newtest@email.com", new ArrayList<>(), new ArrayList<>());
        when(usuarioGateway.atualiza(testId, requestDTO)).thenReturn(Mono.just(responseDTO));

        ResponseEntity<UsuarioResponseDTO> result = usuarioController.atualizarUsuario(testId, requestDTO).block();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(responseDTO, result.getBody());
    }

    @Test
    public void testDeleteById() {
        String testId = "1234";
        doNothing().when(usuarioGateway).deletar(testId);

        ResponseEntity<Void> result = usuarioController.deleteById(testId);

        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }
}
