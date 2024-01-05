package com.fiap.tech_challenge_web_streaming.gateways;

import com.fiap.tech_challenge_web_streaming.controllers.dto.UsuarioRequestDTO;
import com.fiap.tech_challenge_web_streaming.controllers.dto.UsuarioResponseDTO;
import com.fiap.tech_challenge_web_streaming.entities.Usuario;
import com.fiap.tech_challenge_web_streaming.interfaces.UsuarioRepositoryInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        when(usuarioRepository.findAll()).thenReturn(Flux.just(new Usuario(), new Usuario()));

        Flux<UsuarioResponseDTO> result = usuarioGateway.listar();

        StepVerifier.create(result)
                .expectNextCount(2)
                .verifyComplete();

        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Usuario usuario = new Usuario();
        when(usuarioRepository.findById(id)).thenReturn(Mono.just(usuario));

        Mono<Optional<UsuarioResponseDTO>> result = usuarioGateway.listarPorId(id);

        StepVerifier.create(result)
                .expectNext(Optional.of(new UsuarioResponseDTO(
                        usuario.getId(),
                        usuario.getNome(),
                        usuario.getEmail(),
                        usuario.getFavoritos(),
                        usuario.getRecomendados()
                )))
                .verifyComplete();

        verify(usuarioRepository, times(1)).findById(id);
    }

    @Test
    public void testNovo() {
        UsuarioRequestDTO requestDTO = new UsuarioRequestDTO();
        Usuario usuario = new Usuario(requestDTO);
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(Mono.just(usuario));

        Mono<UsuarioResponseDTO> result = usuarioGateway.novo(requestDTO);

        StepVerifier.create(result)
                .expectNext(new UsuarioResponseDTO(
                        usuario.getId(),
                        usuario.getNome(),
                        usuario.getEmail(),
                        usuario.getFavoritos(),
                        usuario.getRecomendados()
                ))
                .verifyComplete();

        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    public void testAtualiza() {
        Long id = 1L;
        UsuarioRequestDTO requestDTO = new UsuarioRequestDTO();
        Usuario usuario = new Usuario(requestDTO);
        when(usuarioRepository.findById(id)).thenReturn(Mono.just(usuario));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(Mono.just(usuario));

        Mono<UsuarioResponseDTO> result = usuarioGateway.atualiza(id, requestDTO);

        StepVerifier.create(result)
                .expectNext(new UsuarioResponseDTO(
                        usuario.getId(),
                        usuario.getNome(),
                        usuario.getEmail(),
                        usuario.getFavoritos(),
                        usuario.getRecomendados()
                ))
                .verifyComplete();

        verify(usuarioRepository, times(1)).findById(id);
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    public void testDeletar() {
        Long id = 1L;
        Usuario usuario = new Usuario();
        when(usuarioRepository.findById(id)).thenReturn(Mono.just(usuario));
        doNothing().when(usuarioRepository).delete(usuario);

        usuarioGateway.deletar(id);

        verify(usuarioRepository, times(1)).findById(id);
        verify(usuarioRepository, times(1)).delete(usuario);
    }
}
