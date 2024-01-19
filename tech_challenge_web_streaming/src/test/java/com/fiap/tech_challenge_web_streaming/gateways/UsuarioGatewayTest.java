package com.fiap.tech_challenge_web_streaming.gateways;

import com.fiap.tech_challenge_web_streaming.controllers.dto.usuario.UsuarioNovoResponseDTO;
import com.fiap.tech_challenge_web_streaming.controllers.dto.usuario.UsuarioRequestDTO;
import com.fiap.tech_challenge_web_streaming.controllers.dto.usuario.UsuarioResponseDTO;
import com.fiap.tech_challenge_web_streaming.entities.Usuario;
import com.fiap.tech_challenge_web_streaming.interfaces.UsuarioRepositoryInterface;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
public class UsuarioGatewayTest {

    @InjectMocks
    private UsuarioGateway usuarioGateway;

    @Mock
    private UsuarioRepositoryInterface usuarioRepository;

    private Usuario usuario;
    private String testId;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        testId = "1234";
        usuario = new Usuario();
        usuario.setId(testId);
        usuario.setNome("Test Name");
        usuario.setEmail("test@email.com");
    }

    @AfterEach
    public void tearDown() {
        testId = null;
        usuario = null;
    }

    @Test
    @DisplayName("Deve retornar todos usuarios")
    public void shouldReturnAllUsers() {

        Usuario usuario1 = new Usuario();
        usuario1.setId("1234");
        usuario1.setNome("Test Name 1");
        usuario1.setEmail("test1@email.com");

        Usuario usuario2 = new Usuario();
        usuario2.setId("5678");
        usuario2.setNome("Test Name 2");
        usuario2.setEmail("test2@email.com");

        when(usuarioRepository.findAll()).thenReturn(Flux.just(usuario1, usuario2));

        Flux<UsuarioResponseDTO> result = usuarioGateway.listar();

        StepVerifier.create(result)
                .expectNextMatches(usuarioResponseDTO ->
                        usuarioResponseDTO.getId().equals(usuario1.getId()) &&
                                usuarioResponseDTO.getNome().equals(usuario1.getNome()) &&
                                usuarioResponseDTO.getEmail().equals(usuario1.getEmail())
                )
                .expectNextMatches(usuarioResponseDTO ->
                        usuarioResponseDTO.getId().equals(usuario2.getId()) &&
                                usuarioResponseDTO.getNome().equals(usuario2.getNome()) &&
                                usuarioResponseDTO.getEmail().equals(usuario2.getEmail())
                )
                .verifyComplete();

        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve retornar o usuario pelo seu id")
    public void shouldReturnUserWithGivenId() {
        when(usuarioRepository.findById(testId)).thenReturn(Mono.just(usuario));

        Mono<Optional<UsuarioResponseDTO>> result = usuarioGateway.listarPorId(testId);

        StepVerifier.create(result)
                .expectNextMatches(optionalUsuarioDTO ->
                        optionalUsuarioDTO.isPresent() &&
                                optionalUsuarioDTO.get().getId().equals(usuario.getId()) &&
                                optionalUsuarioDTO.get().getNome().equals(usuario.getNome()) &&
                                optionalUsuarioDTO.get().getEmail().equals(usuario.getEmail())
                )
                .verifyComplete();

        verify(usuarioRepository, times(1)).findById(testId);
    }


    @Test
    @DisplayName("Deve criar um novo usuario")
    public void shouldCreateNewUser() {
        String expectedId = "1234";
        String expectedName = "Test Name";
        String expectedEmail = "test@email.com";
        UsuarioRequestDTO requestDTO = new UsuarioRequestDTO(expectedName, expectedEmail);
        Usuario expectedUser = new Usuario(requestDTO);
        expectedUser.setId(expectedId);
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(Mono.just(expectedUser));

        Mono<UsuarioNovoResponseDTO> result = usuarioGateway.novo(requestDTO);

        StepVerifier.create(result)
                .expectNextMatches(responseDTO ->
                        responseDTO.getId().equals(expectedId) &&
                                responseDTO.getNome().equals(expectedName) &&
                                responseDTO.getEmail().equals(expectedEmail)
                )
                .verifyComplete();
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    @DisplayName("Deve atualizar o usuario")
    public void shouldUpdateUserDetails() {
        UsuarioRequestDTO requestDTO = new UsuarioRequestDTO("New Test Name", "newtest@email.com");

        Usuario usuario = new Usuario(requestDTO);
        usuario.setId("1234");

        when(usuarioRepository.findById(anyString())).thenReturn(Mono.just(usuario));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(Mono.just(usuario));

        Mono<UsuarioResponseDTO> result = usuarioGateway.atualiza("1234", requestDTO);

        StepVerifier.create(result)
                .expectNextMatches(responseDTO ->
                        responseDTO.getId().equals(usuario.getId()) &&
                                responseDTO.getNome().equals(requestDTO.getNome()) &&
                                responseDTO.getEmail().equals(requestDTO.getEmail())
                )
                .verifyComplete();

        verify(usuarioRepository, times(1)).findById(anyString());
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    @DisplayName("Deve deletar o usuario")
    public void shouldDeleteUserWithGivenId() {
        String testId = "1234";

        when(usuarioGateway.deletar(testId)).thenReturn(Mono.empty());
        usuarioGateway.deletar(testId);

        verify(usuarioGateway, times(1)).deletar(testId);
    }
}
