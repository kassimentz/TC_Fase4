package com.fiap.tech_challenge_web_streaming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class TechChallengeWebStreamingApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechChallengeWebStreamingApplication.class, args);
	}

}
