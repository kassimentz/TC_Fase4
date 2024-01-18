package com.fiap.tech_challenge_web_streaming.repository;

import com.fiap.tech_challenge_web_streaming.entities.Video;
import com.fiap.tech_challenge_web_streaming.interfaces.VideoRepositoryInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@DataMongoTest
public class VideoRepositoryTest {

    @Autowired
    private VideoRepositoryInterface videoRepository;

    private Video video;

    @BeforeEach
    public void setUp() {
        video = new Video();
        video.setId("5678");
        video.setTitulo("Test Title");
        video.setDescricao("Test Description");
        video.setUrl("http://test.com");
        videoRepository.save(video).block();
    }

    @Test
    public void testFindById() {
        Mono<Video> videoMono = videoRepository.findById("5678");

        StepVerifier.create(videoMono)
                .expectNextMatches(foundVideo ->
                        foundVideo.getId().equals(video.getId()) &&
                                foundVideo.getTitulo().equals(video.getTitulo()) &&
                                foundVideo.getDescricao().equals(video.getDescricao()) &&
                                foundVideo.getUrl().equals(video.getUrl())
                )
                .verifyComplete();
    }

    @Test
    public void testFindAll() {
        Flux<Video> videoFlux = videoRepository.findAll();

        StepVerifier.create(videoFlux)
                .expectNextMatches(foundVideo ->
                        foundVideo.getId().equals(video.getId()) &&
                                foundVideo.getTitulo().equals(video.getTitulo()) &&
                                foundVideo.getDescricao().equals(video.getDescricao()) &&
                                foundVideo.getUrl().equals(video.getUrl())
                )
                .verifyComplete();
    }

    @Test
    public void testSave() {
        Video newVideo = new Video();
        newVideo.setId("1234");
        newVideo.setTitulo("New Test Title");
        newVideo.setDescricao("New Test Description");
        newVideo.setUrl("http://newtest.com");

        Mono<Video> savedVideoMono = videoRepository.save(newVideo);

        StepVerifier.create(savedVideoMono)
                .expectNextMatches(savedVideo ->
                        savedVideo.getId().equals(newVideo.getId()) &&
                                savedVideo.getTitulo().equals(newVideo.getTitulo()) &&
                                savedVideo.getDescricao().equals(newVideo.getDescricao()) &&
                                savedVideo.getUrl().equals(newVideo.getUrl())
                )
                .verifyComplete();
    }

    @Test
    public void testDelete() {
        Mono<Void> deleted = videoRepository.delete(video);

        StepVerifier.create(deleted)
                .verifyComplete();

        Mono<Video> videoMono = videoRepository.findById(video.getId());

        StepVerifier.create(videoMono)
                .verifyComplete();
    }
}
