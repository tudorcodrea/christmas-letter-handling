package com.christmas.letter.sender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LetterProcessorServiceApplication {

	public static void main(String... args) {
		SpringApplication.run(LetterProcessorServiceApplication.class, args);
	}
}
