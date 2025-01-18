package net.etfbl.indeks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class IndeksBackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(IndeksBackendApplication.class, args);
	}
}
