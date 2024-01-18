package com.fiap.tech_challenge_web_streaming.gateways;

import com.fiap.tech_challenge_web_streaming.entities.Video;
import com.fiap.tech_challenge_web_streaming.gateways.VideoGateway;
import com.fiap.tech_challenge_web_streaming.interfaces.VideoRepositoryInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class VideoGatewayTest {

    @InjectMocks
    private VideoGateway videoGateway;

    @Mock
    private VideoRepositoryInterface videoRepository;

    private Video video;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        video = new Video();
        video.setId("5678");
        video.setTitulo("Test Title");
        video.setDescricao("Test Description");
        video.setUrl("http://test.com");
    }

    @Test
    public void testListar() {
        when(videoRepository.findAll()).thenReturn(Flux.just(video));

        Pageable pageable = PageRequest.of(0, 10); // page 0, 10 items per page

// Call the method with the Pageable object
        Flux<Page<Video>> result = videoGateway.getAllVideos(pageable, null, null, null);;

        Video expectedResponse = new Video(
                video.getId(),
                video.getTitulo(),
                video.getDescricao(),
                video.getUrl(),
                video.getDataPublicacao(),
                video.getCategoria(),
                video.getFavoritadoPorUsuarios()
        );

        assertEquals(expectedResponse, result.blockFirst());
    }

    @Test
    public void testListarPorId() {
        when(videoRepository.findById(video.getId())).thenReturn(Mono.just(video));

        Mono<Video> result = videoGateway.getVideoById(video.getId());

        Video expectedResponse = new Video(
                video.getId(),
                video.getTitulo(),
                video.getDescricao(),
                video.getUrl(),
                video.getDataPublicacao(),
                video.getCategoria(),
                video.getFavoritadoPorUsuarios()
        );

        assertEquals(expectedResponse, result.block());
    }

    @Test
    public void testNovo() {
        when(videoRepository.save(video)).thenReturn(Mono.just(video));

        Mono<Video> result = videoGateway.createVideo(video);

        Video expectedResponse = new Video(
                video.getId(),
                video.getTitulo(),
                video.getDescricao(),
                video.getUrl(),
                video.getDataPublicacao(),
                video.getCategoria(),
                video.getFavoritadoPorUsuarios()
        );

        assertEquals(expectedResponse, result.block());
    }

    @Test
    public void testAtualiza() {
        when(videoRepository.findById(video.getId())).thenReturn(Mono.just(video));
        when(videoRepository.save(video)).thenReturn(Mono.just(video));

        Mono<Video> result = videoGateway.updateVideo(video.getId(), video);

        Video expectedResponse = new Video(
                video.getId(),
                video.getTitulo(),
                video.getDescricao(),
                video.getUrl(),
                video.getDataPublicacao(),
                video.getCategoria(),
                video.getFavoritadoPorUsuarios()
        );

        assertEquals(expectedResponse, result.block());
    }

    @Test
    public void testDeletar() {
        when(videoRepository.findById(video.getId())).thenReturn(Mono.just(video));
        doNothing().when(videoRepository).delete(video);

        videoGateway.deleteVideo(video.getId());

        verify(videoRepository, times(1)).delete(video);
    }
}
