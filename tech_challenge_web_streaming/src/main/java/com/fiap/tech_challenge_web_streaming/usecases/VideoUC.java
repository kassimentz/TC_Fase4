package com.fiap.tech_challenge_web_streaming.usecases;

import com.fiap.tech_challenge_web_streaming.interfaces.VideoRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VideoUC {


    @Autowired
    VideoRepositoryInterface videoRepository;
}
