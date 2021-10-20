package br.com.wm.lembreteapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LembreteApiApplication {

	public static void main(String[] args) {
		System.setProperty("spring.profiles.active", "prod");
		SpringApplication.run(LembreteApiApplication.class, args);
	}

}
