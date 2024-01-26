package com.fiap.tech_challenge_web_streaming.infrastructure.video.repository;

import com.fiap.tech_challenge_web_streaming.infrastructure.video.entityschema.VideoEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface VideoRepository extends ReactiveMongoRepository <VideoEntity, String> {



}
